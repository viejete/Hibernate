import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManagerPet {

    private static SessionFactory factory;

    public static void main(String[] args) {

        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManagerPet MP = new ManagerPet();

      /* Let us have a set of owners for the first pet
        HashSet pets = new HashSet();

        pets.add(new Pet(true , "Ash\'ana" , "beast" , "ability_mount_whitetiger" , "critter" , 7 , "flying" , 1 , 147 , 10 , 9));
        pets.add(new Pet(false , "Argent Gruntling" , "humanoid" , "reputation_argentchampion" , "dragonkin" , 0 , "beast" , 1 , 147 , 9 , 9));
        pets.add(new Pet(true , "Celestial Calf" , "magical" , "inv_pet_celestialbabyhippo" , "flying" , 5 , "mechanical" , 1 , 147 , 9 , 9));*/

      /* Add pet records in the database */
        Integer petId1 = MP.addPet(true , "Ash\'ana" , "beast" , "ability_mount_whitetiger" , "critter" , 7 , "flying" , 1 , 147 , 10 , 9);

      /* Add another pet record in the database */
        Integer petId2 = MP.addPet(false , "Argent Gruntling" , "humanoid" , "reputation_argentchampion" , "dragonkin" , 0 , "beast" , 1 , 147 , 9 , 9);

      /* List down all the pets */
        MP.listPets();

      /* Update pet's family records */
        MP.updatePet(petId1, "magical");

      /* Delete a pet from the database */
        MP.deletePet(petId2);

      /* List down all the pets */
        MP.listPets();

    }

    /* Method to add a pet record in the database */
    public Integer addPet(boolean canBattle, String name, String family, String icon, String strongAgainst, int typeId, String weakAgainst, int petQualityId, int health, int power, int speed) {

        Session session = factory.openSession();
        Transaction tx = null;
        Integer petId = null;

        try{

            tx = session.beginTransaction();
            Pet pet = new Pet(canBattle, name, family, icon, strongAgainst, typeId, weakAgainst, petQualityId, health, power, speed);
            //owner.setPets(pets);
            petId = (Integer) session.save(pet);
            tx.commit();

        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return petId;
    }

    /* Method to list all the pet details */
    public void listPets() {

        Session session = factory.openSession();
        Transaction tx = null;

        try{

            tx = session.beginTransaction();
            List pets = session.createQuery("FROM Pet").list();

            for (Iterator iterator1 = pets.iterator(); iterator1.hasNext();) {

                Pet pet = (Pet) iterator1.next();
                System.out.print("Can Battle: " + pet.isCanBattle());
                System.out.print("  Name: " + pet.getName());
                System.out.print("  Family: " + pet.getFamily());
                System.out.print("  Icon: " + pet.getIcon());
                System.out.print("  Strong Against: " + pet.getStrongAgainst());
                System.out.print("  Type Id: " + pet.getTypeId());
                System.out.print("  Weak Against: " + pet.getWeakAgainst());
                System.out.print("  Pet Quality: " + pet.getPetQualityId());
                System.out.print("  Health: " + pet.getHealth());
                System.out.print("  Power: " + pet.getPower());
                System.out.println("  Speed: " +pet.getSpeed());
                //Set pets = owner.getPets();

                /*for (Iterator iterator2 = pets.iterator(); iterator2.hasNext();){

                    Pet petName = (Pet) iterator2.next();
                    System.out.println("Pet Name: " + petName.getName());
                }*/
            }

            tx.commit();

        }catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }finally {
            session.close();
        }
    }

    /* Method to update family for a pet */
    public void updatePet(Integer petId, String family){

        Session session = factory.openSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            Pet pet = session.get(Pet.class, petId);
            pet.setFamily(family);
            session.update(pet);
            tx.commit();

        }catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }finally {
            session.close();
        }
    }

    /* Method to delete a pet from the records */
    public void deletePet (Integer petId) {

        Session session = factory.openSession();
        Transaction tx = null;

        try{

            tx = session.beginTransaction();
            Pet pet = session.get(Pet.class, petId);
            session.delete(pet);
            tx.commit();

        }catch (HibernateException e) {

            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }finally {
            session.close();
        }
    }
}
