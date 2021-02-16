package typingjump;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @authors Raeda azkoul, Noor Al-Hafez, Fatema Bayat, Aisha Farouque, Fatimah
 * Jabr
 */
public class TextSelector {

    Object[] strings;

    public TextSelector(String type) {
        final Session session = HibernateUtilPlayText.getSessionFactory().openSession();
        final Transaction t1 = session.beginTransaction();
        String queryStr = "Select text FROM playtext WHERE type = '" + type + "'";
        Query query = session.createQuery(queryStr);
        List<String> pList = null;
        pList = query.list();
        session.close();

        strings = pList.toArray();
        for (Object string : strings) {
            System.out.println((String) string);
        }
    }

    public String getNextWord() {

        return (String) strings[(int) (Math.random() * strings.length)];
    }
}
