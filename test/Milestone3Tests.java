import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.Account;
import models.Offer;
import models.Person;
import models.SearchAttributes;
import models.SortAttribute;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import play.db.jpa.JPA;
import repository.AccountRepository;
import repository.OfferRepository;
import repository.PersonRepository;
import serviceImpl.DiscoveryServiceImpl;

public class Milestone3Tests extends Assert {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPU");

	private AccountRepository accountRepo = new AccountRepository();
	

	@Before
	public void begin() {
		JPA.bindForCurrentThread(emf.createEntityManager());
		JPA.em().getTransaction().begin();
	}

	@After
	public void end() {
		JPA.em().getTransaction().commit();
	}
	
	@Test
	public void testDbConnection() {
		String email = "test@test.com";
		Account a = new Account();
		a.email = email;
		accountRepo.createAccount(a);
		
		Account res = accountRepo.findAccountByMail(email);
		assertNotNull(res);
	}
	
	@Test
	public void foreignKeyTest() {
		Person p = new Person();
		p.surname = "Haase";
		Person pp = PersonRepository.createPerson(p);
		 
		
		Offer o = new Offer();
		o.geolocX = 48.0;
		o.geolocY = 7.0;
		o.owner = pp;
		OfferRepository.createOffer(o);
		
		List<Offer> res = OfferRepository.findOfferByOwner(pp);
		assertNotNull(res.get(0));
		assertTrue(res.get(0).geolocX == 48.0);
		
	}
	
	@Test
	public void testGeoForecasting() {
		String test = Arrays.toString(OfferRepository.boundingBoxGenerator(48.193005, 11.4841205, 1));
		assertTrue("[48.1840218471588, 48.201988152841196, 11.479015963357584, 11.497355793956412]".equals(test) );
	}
	
	@Test
	public void testSQLGenerator() {
		SearchAttributes sa = new SearchAttributes();
		sa.radius = 1;
		sa.lat = 11.0;
		sa.lng = 48.0;
		String sql1 = OfferRepository.queryGenerator(sa);
		
		sa.maxPrice = 15;
		String sql2 = OfferRepository.queryGenerator(sa);
		
		sa.spaceSize = 23;
		String sql3 = OfferRepository.queryGenerator(sa);
		
		sa.from = new Timestamp(10000);
		String sql4 = OfferRepository.queryGenerator(sa);
		
		assertTrue(sql1.equalsIgnoreCase("select * from offer o where o.geolocX between 48.0035621362456 and 48.0304218637544 and o.geolocY between 10.991016847158805 and 11.008983152841195 and o.is_active = true"));
		assertTrue(sql2.equalsIgnoreCase("select * from offer o where o.geolocX between 48.0035621362456 and 48.0304218637544 and o.geolocY between 10.991016847158805 and 11.008983152841195 and o.is_active = true and o.price <= 15.0"));
		assertTrue(sql3.equalsIgnoreCase("select * from offer o where o.geolocX between 48.0035621362456 and 48.0304218637544 and o.geolocY between 10.991016847158805 and 11.008983152841195 and o.is_active = true and o.price <= 15.0 and o.space_size >= 23.0"));
		assertTrue(sql4.equalsIgnoreCase("select * from offer o where o.geolocX between 48.0035621362456 and 48.0304218637544 and o.geolocY between 10.991016847158805 and 11.008983152841195 and o.is_active = true and o.price <= 15.0 and o.space_size >= 23.0 and to_timestamp(10000) not between" + 
		" o.contracted_from and o.contracted_until and to_timestamp(10000) not between o.contracted_from and o.contracted_until and to_timestamp(10000) between o.offer_from and o.offer_to and to_timestamp(10000) between o.offer_from and o.offer_to"));
		
		sa.maxPrice = 0;
		String sql5 = OfferRepository.queryGenerator(sa);
		assertTrue(sql5.equalsIgnoreCase("select * from offer o where o.geolocX between 48.0035621362456 and 48.0304218637544 and o.geolocY between 10.991016847158805 and 11.008983152841195 and o.is_active = true and o.space_size >= 23.0 and to_timestamp(10000) not between" + 
				" o.contracted_from and o.contracted_until and to_timestamp(10000) not between o.contracted_from and o.contracted_until and to_timestamp(10000) between o.offer_from and o.offer_to and to_timestamp(10000) between o.offer_from and o.offer_to"));
				
	}
	
	
	@Test
	public void testSearch() {
		
		SearchAttributes sa = new SearchAttributes();
		sa.radius = 10;
		sa.lat = 11.0;
		sa.lng = 48.0;
		
		Offer o1 = new Offer();
		o1.geolocX = 48.0;
		o1.geolocY = 11.0001;
		o1.isActive = true;
		OfferRepository.createOffer(o1);
		
		Offer o2 = new Offer();
		o2.geolocX = 48.0;
		o2.geolocY = 11.0002;
		o2.isActive = true;
		OfferRepository.createOffer(o2);
		
		Offer o3 = new Offer();
		o3.geolocX = 48.0;
		o3.geolocY = 11.0003;
		o3.isActive = true;
		OfferRepository.createOffer(o3);
		
		DiscoveryServiceImpl ds = new DiscoveryServiceImpl();
		List<Offer> offers = ds.findOffersSortBy(sa, SortAttribute.Distance);
		boolean case1 = offers.get(0).distance < offers.get(1).distance && offers.get(1).distance < offers.get(2).distance ;
		boolean case2 = offers.get(0).distance > offers.get(1).distance && offers.get(1).distance > offers.get(2).distance ;
		
		assertTrue(case1 || case2);
	}
	

}
