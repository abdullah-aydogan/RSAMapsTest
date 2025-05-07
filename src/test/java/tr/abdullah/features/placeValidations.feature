Feature: RSAMaps API Doğrulama Islemleri

  @AddPlace
  Scenario Outline: Yeni mekan ekleme
    Given "<name>" isminde "<language>" dilinde ve "<address>" adresinde yeni bir mekan olustur
    When "AddPlace" API kullanarak "POST" HTTP request gonder
    Then Request sonrası status kodun 200 oldugunu kontrol et
    And Donen response uzerinde "status" degerinin "OK" oldugunu kontrol et
    And Donen response uzerinde "scope" degerinin "APP" oldugunu kontrol et

  Examples:
    | name     | language | address |
    | Istanbul | Turkish  | Turkey  |
    | Berlin   | German   | Germany |
    | Paris    | France   | French  |