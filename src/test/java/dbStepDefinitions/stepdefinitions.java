package dbStepDefinitions;

import io.cucumber.java.en.Given;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class stepdefinitions {
    String url="jdbc:sqlserver://184.168.194.58:1433;databaseName=hotelmycamp ; user=techproed;password=P2s@rt65";
    String username="techproed";
    String password="P2s@rt65";

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Given("kullanici HMC veri tabanina baglanir")
    public void kullanici_hmc_veri_tabanina_baglanir() throws SQLException {
        //database e baglanti kuruyoruz
    connection= DriverManager.getConnection(url,username,password);
    statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

    }
    @Given("kullanici {string} tablosundaki {string} verilerini alir")
    public void kullanici_tablosundaki_verilerini_alir(String table, String field) throws SQLException {
        //Query calistiriyoruz
        String readQuery="SELECT" + field + "FROM"+ table;
        resultSet=statement.executeQuery(readQuery);
    }
    @Given("kullanici {string} sutunundaki verileri okur")
    public void kullanici_sutunundaki_verileri_okur(String field) throws SQLException {
        //resultSet bizim su ana kadar kullandigimiz Set yapisinda degildir.
        //resultSet iterator ile calisir.
        //resultSet teki bilgileri okumak istiyorsaniz önce iterator ü istediginiz yere gondermelisiniz.

        resultSet.first(); //bu komut iterator ü ilk elemente goturur. gitti ise true gidemezse false doner.
        //ıterator istenen komuta gidince artik elementi yazdirabiliriz.

        System.out.println(resultSet.getString(field));

        //2'inci oda fiyatini gormek istersek,
        //iterator ü yollamamiz lazim

        resultSet.next();
        System.out.println(resultSet.getString(field));

        //tum price sutununu yazdirmak istesem;
        System.out.println("============================================");
        //resultSet ile calisirken iterator konumunu kontrol etmek zorundayiz.
        //eger 1. elemana donmeden listeyi yazdirmaya devam edersem
        //kaldigi yerden devam edip 4. element ve sonrasini yazdirir.

        resultSet.absolute(0);

        while(resultSet.next()){
            System.out.println(resultSet.getString(field));
        }

        //price sutununda kac data oldugunu yazalim
        //while loop ile resultSet.next() false oluncaya kadar gittik.
        //Dolayisi ile resultSet su anda sonra

        resultSet.last();
        System.out.println(resultSet.getRow());

        //su anda tüm price bilgileri resultSet üzerinde
        //eger bu bilgilerle birden fazla test yapacaksak7
        //bu bilgileri java ortamina almakta fayda var.
        //ornegin bir List<Double> olusturup
        //tum price verilerini bu listeye ekleyebiliriz.
        //boylece bir java obj si olan List sayesinde
        //price degerleri üzerinde istedigimiz testleri yapabiliriz.
        resultSet.absolute(0);
        List<Double>priceList=new ArrayList<>();

        while (resultSet.next()){
            priceList.add(resultSet.getDouble(field));
        }

    }

}
