### Software yang di butuhkan : ###
* Jdk versi 1.6 atau lebih
* Blue J
* mysql (xampp)
* dll

### Langkah -langkah Instalasi ###
* Buatlah database baru dengan nama terserah anda
* Import file yang ada di folder database yaitu j_klinikhewan.sql ke database anda
* Ubahlah pengaturan user dan password database anda di dalam file Koneksidatabase.java
```
host = "jdbc:mysql://localhost/[nama_database_anda]";
user = "[user_database]";
pass = "[pass_databased]";
```
* Kemudian hapus extensi .txt yang ada di dalam folder lib
* Buat Projek baru pada aplikasi BlueJ anda
* Pindahkan semua file dan direktori hasil download ke dalam projek
* Lalu tambahakan libraries pada folder lib ke BlueJ anda
* Compile semua file dan program klik hewan siap di jalankan

Terima kasih