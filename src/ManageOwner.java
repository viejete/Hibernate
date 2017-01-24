import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageOwner {

    private static SessionFactory factory;

    public static void main(String[] args) {

        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManageOwner MO = new ManageOwner();

      /* Let us have a set of pets for the first owner  */
        HashSet pets = new HashSet();

        pets.add(new Pet(true , "Ash\'ana" , "beast" , "ability_mount_whitetiger" , "critter" , 7 , "flying" , 1 , 147 , 10 , 9));
        pets.add(new Pet(false , "Argent Gruntling" , "humanoid" , "reputation_argentchampion" , "dragonkin" , 0 , "beast" , 1 , 147 , 9 , 9));
        pets.add(new Pet(true , "Celestial Calf" , "magical" , "inv_pet_celestialbabyhippo" , "flying" , 5 , "mechanical" , 1 , 147 , 9 , 9));
     
      /* Add owner records in the database */
        Integer ownerId1 = MO.addOwner("Carlos", "Sarga", "Humano", pets);

      /* Add another owner record in the database */
        Integer ownerId2 = MO.addOwner("Daniel", "Muchi", "Humana", pets);

      /* List down all the owners */
        MO.listOwners();

      /* Update owner's race records */
        MO.updateOwner(ownerId1, "No-muerto");

      /* Delete an owner from the database */
        MO.deleteOwner(ownerId2);

      /* List down all the owners */
        MO.listOwners();

    }

    /* Method to add an employee record in the database */
    public Integer addOwner(String fname, String lname, String race, Set pets) {

        Session session = factory.openSession();
        Transaction tx = null;
        Integer ownerId = null;

        try{

            tx = session.beginTransaction();
            Owner owner = new Owner(fname, lname, race);
            owner.setPets(pets);
            ownerId = (Integer) session.save(owner);
            tx.commit();

        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return ownerId;
    }

    /* Method to list all the owner details */
    public void listOwners() {

        Session session = factory.openSession();
        Transaction tx = null;

        try{

            tx = session.beginTransaction();
            List owners = session.createQuery("FROM Owner").list();

            for (Iterator iterator1 = owners.iterator(); iterator1.hasNext();) {

                Owner owner = (Owner) iterator1.next();
                System.out.print("First Name: " + owner.getFirstName());
                System.out.print("  Last Name: " + owner.getLastName());
                System.out.println("  Race: " + owner.getRace());
                Set pets = owner.getPets();

                for (Iterator iterator2 = pets.iterator(); iterator2.hasNext();){

                    Pet petName = (Pet) iterator2.next();
                    System.out.println("Pet Name: " + petName.getName());
                }
            }

            tx.commit();

        }catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }finally {
            session.close();
        }
    }

    /* Method to update race for an owner */
    public void updateOwner(Integer ownerId, String race){

        Session session = factory.openSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            Owner owner = session.get(Owner.class, ownerId);
            owner.setRace(race);
            session.update(owner);
            tx.commit();

        }catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }finally {
            session.close();
        }
    }

    /* Method to delete an owner from the records */
    public void deleteOwner (Integer ownerId) {

        Session session = factory.openSession();
        Transaction tx = null;

        try{

            tx = session.beginTransaction();
            Owner owner = session.get(Owner.class, ownerId);
            session.delete(owner);
            tx.commit();

        }catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }finally {
            session.close();
        }
    }
}