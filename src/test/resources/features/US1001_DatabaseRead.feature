Feature: US1001 Kullanici Database'e baglanip bilgileri okuyabilir
  @db
  Scenario: TC01 kullanici database'e baglanip istedigi bilgileri okuyabilmeli

    Given kullanici HMC veri tabanina baglanir
    # Database ye baglanacagiz
    And kullanici "tHOTELROOM" tablosundaki "Price" verilerini alir
    # Query calistiracagiz SELECT Price FROM tHOTELROOM
    And kullanici "Price" sutunundaki verileri okur
    #Bu adimda database sorgusu sonunda bize dodurulen bilgiyi nasil kullanacagimizi gorururz