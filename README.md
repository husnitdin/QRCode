Generate QR code and save in qr_codes folder of resources
GET
1. http://localhost:8080/api/qrcode/generate?data=https://www.google.com/

Generate QR code in UI with thymeleaf
GET
2. http://localhost:8080/api/qrcode/generate-qr?data=https://www.google.com/

Read QR code saved 
POST
3. http://localhost:8080/api/qrcode/read
in postman -> body -> form-data -> Key => file, type => File, Value => select QR code image
