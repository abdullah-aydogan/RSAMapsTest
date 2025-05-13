Feature: RSAMaps API Doğrulama Islemleri

  @AddPlace
  Scenario Outline: Yeni mekan ekleme
    Given "<name>" isminde "<language>" dilinde ve "<address>" adresinde yeni bir mekan olustur
    When "AddPlace" API kullanarak "POST" HTTP request gonder
    Then Request sonrasi status kodun 200 oldugunu kontrol et
    And Donen response uzerinde "status" degerinin "OK" oldugunu kontrol et
    And Donen response uzerinde "scope" degerinin "APP" oldugunu kontrol et

  Examples:
    | name     | language | address |
    | Istanbul | Turkish  | Turkey  |
    | Berlin   | German   | Germany |
    | Paris    | France   | French  |

  @GetPlace
  Scenario: Mekan bilgilerini goruntuleme
    Given "Pendik" isminde "Turkish" dilinde ve "Istanbul" adresinde yeni bir mekan olustur
    And "AddPlace" API kullanarak "POST" HTTP request gonder
    When Request sonrasi mekanin ID bilgisini al
    And "GetPlace" API kullanarak "GET" HTTP request gonder
    Then Request sonrasi status kodun 200 oldugunu kontrol et
    And Donen response uzerinde "name" degerinin "Pendik" oldugunu kontrol et

  @UpdatePlace
  Scenario: Mekan bilgilerini guncelleme
    Given "Gebze" isminde "Turkish" dilinde ve "Kocaeli" adresinde yeni bir mekan olustur
    And "AddPlace" API kullanarak "POST" HTTP request gonder
    When Request sonrasi mekanin ID bilgisini al
    And Mekan adresini "Izmit" olarak guncellemek icin veri hazirla
    And "UpdatePlace" API kullanarak "PUT" HTTP request gonder
    Then Request sonrasi status kodun 200 oldugunu kontrol et
    And "GetPlace" API kullanarak "GET" HTTP request gonder
    And Donen response uzerinde "address" degerinin "Izmit" oldugunu kontrol et