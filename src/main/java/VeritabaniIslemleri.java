/*
 * Copyright 2019 Universal Bilgi Teknolojileri.
 *
 * UKL 1.1 lisansı ile lisanslanmıştır. Bu dosyanın l,isans koşullarına uygun
 * olmayan şekilde kullanımı yasaklanmıştır. Lisansın bir kopyasını aşağıdaki
 * linkten edinebilirsiniz.
 *
 * http://www.uni-yaz.com/lisans/ukl_1_1.pdf
 *
 * Yasalar aksini söylemediği veya yazılı bir sözleşme ile aksi belirtilmediği sürece,
 * bu yazılım mevcut hali ile hiç bir garanti vermeden veya herhangi bir şart ileri
 * sürmeden dağıtılır. Bu yazılımın edinim izinleri ve limitler konusunda lisans
 * sözleşmesine bakınız.
 *
 */

import java.sql.*;

/**
 * VeritabaniIslemleri
 *
 * @author Şafak Taşkın
 * @since 5.187
 */
public class VeritabaniIslemleri {

    final static String JDBC_CONNECTION_STR = "jdbc:mysql://127.0.0.1:3306/jdbc";
    final static String USERNAME = "root";
    final static String PASSWORD = "root";

    public boolean baglantiyiKontrolEt() {

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD)) {
            if (conn != null) {
                return true;
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void kahramanEkle(Hero hero) {

        String sql = "insert into hero (adi, soyadi) " +
                "values (?, ?) ";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, hero.getAdi());
            preparedStatement.setString(2, hero.getSoyadi());

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " kahraman eklendi.");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void kahramanlariListele() {

        String sql = "Select * from hero";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String adi = resultSet.getString("adi");
                String soyadi = resultSet.getString("soyadi");

                System.out.printf(id + " - " + adi + " - " + soyadi + "\n");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void filmEkle(String filmAdi, float budget, int heroId) {

        String sql = "insert into movie (filmAdi, budget, heroId) " +
                "values (?, ?, ?) ";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, filmAdi);
            preparedStatement.setFloat(2, budget);
            preparedStatement.setInt(3, heroId);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " film eklendi.");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filmleriListele() {

        String sql = "Select * from movie";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String filmAdi = resultSet.getString("filmAdi");
                String budget = resultSet.getString("budget");

                System.out.printf(id + " - " + filmAdi + " - " + budget + "\n");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void kahramanlarinKazanclari(){

        String sql = "select concat(h.adi,\" \",h.soyadi) as HERO, " +
                "sum(m.budget) as TOTAL_BUDGET " +
                "from hero h left join movie m on h.id=m.id " +
                "group by h.adi order by sum(m.budget) desc";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet =  preparedStatement.executeQuery();
            while (resultSet.next()) {
                String adiSoyadi = resultSet.getString("HERO");
                int total_budget = resultSet.getInt("TOTAL_BUDGET");
                System.out.printf(adiSoyadi + " - " + total_budget + "\n");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void karaktereAitFilmleriSaydir(){
        String sql = "select concat(h.adi,' ',h.soyadi) as HERO , " +
                "count(m.filmAdi) as ADET from hero h inner join " +
                "movie m on h.id=m.heroId group by h.adi " +
                "order by ADET desc";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()) {
                String adiSoyadi = resultSet.getString("HERO");
                int adet = resultSet.getInt("ADET");
                System.out.printf(adiSoyadi + " - " + adet + "\n");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
