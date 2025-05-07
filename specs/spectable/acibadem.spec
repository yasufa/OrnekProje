# Acıbadem IPS Senaryoları
tags : IPS

## TC1- Hatalı Kullanıcı Kodu ile Giriş Yapılması
tags: Basarisiz_kullanici, login, TC1
* "Key_KullaniciAdi_AcibademSigortaHatali" kullanıcı adı ve "Key_Sifre" şifresi ile giriş yap
* "Hatalı kullanıcı/şifre girilmiştir." uyarısının çıktığı kontrol edilir.

## TC2- Hatalı Kullanıcı Şifresi ile Girişi Yapılması
tags: Basarisiz_kullanici, login, TC2

* "Key_KullaniciAdi_AcibademSigortaHatali" kullanıcı adı ve "Key_Sifre_AcibademSigortaHatali" şifresi ile giriş yap
* "Hatalı kullanıcı/şifre girilmiştir." uyarısının çıktığı kontrol edilir.

## TC3- Kullanıcı Bilgilerinin Doğruluğunun Kontrolü
tags: Basarili_kullanici, login, TC3, Smoke

* "Key_KullaniciAdi" kullanıcı adı ve "Key_Sifre" şifresi ile giriş yap
* Kullanıcıya ait bilgilerden eczane adının "KAYA ECZANESİ" olduğu kurum kodunun "13919" olduğu kontrol edilir.

## TC4 - TCKN ile Provizyon oluşturma-Acıbadem Sigorta
tags: TCKN ile Provizyon oluşturma-Acıbadem Sigorta, TC4

* "Key_KullaniciAdi" kullanıcı adı ve "Key_Sifre" şifresi ile giriş yap
* "Sigortalı Arama" menüsünden sigorta şirketi "AcıbademSigorta" seçilerek sigortalı arama ekranı açılır
* Default olarak "T.C Kimlik Numarası" seçiminin geldiği kontrol edilir ve "Key_TCNo" değeri ile sigortalı sorgulaması yapılır.
* Popup ta "Bupa Acıbadem Sigorta sigortalısına hizmet vermektesiniz." yazısının bulunduğunu kontrol edilir.
* Reçete Giriş Ekranı'nın açıldığı görülür.
* Reçete giriş ekranından reçete tipi "Ayaktan Tedavi Reçetesi" seçilir.
* Reçete tarihi bugünün tarihinden "0" gün kadar uzaklıktaki tarih yazılarak devam edilir.
* Doktor adı "Key_IstediginizDoktorAdiniGir", doktor branşı "Key_Doktorbransi" ve akademik kariyer "Key_DoktorAkademikKariyer" olarak seçilir.
* İlaç bilgileri alanında işlem tipi "Key_IslemTipi", ilaç ekleme türü "Key_IlacEklemeTuru" seçilerek barkod bilgisi "Key_BarkodBilgisi" olarak yazılır.
* Sigorta kullanım alanı default değeri olarak seçilir ve ilaç ara butonuna basılır.
* Listelenen ilacın kullanım şekli bilgisi "Key_IlacKullanimSekli" olarak seçilir ve provizyon oluştur butonuna tıklanır.
* Provizyon form döküm sayfası kapatılır.

## TC5 - TCKN ile Provizyon oluşturma-Majistral İşlem Tipi Seçilerek Sonlandırma Hatası
tags: Majistral İşlem Tipi Seçilerek Sonlandırma Hatası, TC5

* "Key_KullaniciAdi" kullanıcı adı ve "Key_Sifre" şifresi ile giriş yap
* "Sigortalı Arama" menüsünden sigorta şirketi "AcıbademSigorta" seçilerek sigortalı arama ekranı açılır
* Default olarak "T.C Kimlik Numarası" seçiminin geldiği kontrol edilir ve "Key_TCNo" değeri ile sigortalı sorgulaması yapılır.
* Popup ta "Bupa Acıbadem Sigorta sigortalısına hizmet vermektesiniz." yazısının bulunduğunu kontrol edilir.
* Reçete Giriş Ekranı'nın açıldığı görülür.
* Reçete giriş ekranından reçete tipi "Ayaktan Tedavi Reçetesi" seçilir.
* Reçete tarihi bugünün tarihinden "0" gün kadar uzaklıktaki tarih yazılarak devam edilir.
* Doktor adı "Key_IstediginizDoktorAdiniGir", doktor branşı "Key_Doktorbransi" ve akademik kariyer "Key_DoktorAkademikKariyer" olarak seçilir.
* İlaç bilgileri alanında işlem tipi "Key_IslemTipiMajistral" olan seçilir
* Provizyon oluştur butonuna tıklanır.
* "Lütfen, İlaç girişi yapınız!" uyarısının çıktığı kontrol edilir.


## TC6 - TCKN ile Provizyon oluşturma-Majistral İşlem Tipi - Preperat Hatası
tags: Preperat Hatası, TC6

* "Key_KullaniciAdi" kullanıcı adı ve "Key_Sifre" şifresi ile giriş yap
* "Sigortalı Arama" menüsünden sigorta şirketi "AcıbademSigorta" seçilerek sigortalı arama ekranı açılır
* Default olarak "T.C Kimlik Numarası" seçiminin geldiği kontrol edilir ve "Key_TCNo" değeri ile sigortalı sorgulaması yapılır.
* Popup ta "Bupa Acıbadem Sigorta sigortalısına hizmet vermektesiniz." yazısının bulunduğunu kontrol edilir.
* Reçete Giriş Ekranı'nın açıldığı görülür.
* Reçete giriş ekranından reçete tipi "Ayaktan Tedavi Reçetesi" seçilir.
* Reçete tarihi bugünün tarihinden "0" gün kadar uzaklıktaki tarih yazılarak devam edilir.
* Doktor adı "Key_IstediginizDoktorAdiniGir", doktor branşı "Key_Doktorbransi" ve akademik kariyer "Key_DoktorAkademikKariyer" olarak seçilir.
* İlaç bilgileri alanında işlem tipi "Key_IslemTipiMajistral" olan seçilir
* Majistral İlaç Ekle butonuna tıklanır
* Majistral İlaç Ekle Devam Et butonuna tıklanır
* "Preparat Türünü Seçiniz..." uyarısının çıktığı kontrol edilir.




