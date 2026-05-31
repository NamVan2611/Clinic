# Clinic

Ứng dụng backend cho phòng khám (Spring Boot) — quản lý bệnh nhân, bác sĩ, lịch hẹn, đơn thuốc và xác thực.

## Công nghệ
- Java 17+
- Spring Boot
- Spring Security (nếu có cấu hình)
- JWT (nếu sử dụng trong dự án)
- Spring Data JPA
- Maven
- MapStruct
- Lombok

## Yêu cầu
- JDK 17 hoặc mới hơn
- Maven 3.6+

## Cấu trúc chính
- `com.clinic.controller` — API controllers (Auth, Doctor, Patient, Appointment, Prescription)
- `com.clinic.service` — business logic
- `com.clinic.repository` — JPA repositories
- `src/main/resources` — cấu hình (`application.yml`, `application-dev.yml`, `application-test.yml`)

## Các endpoint chính
(Dựa trên controller trong mã nguồn)
- `POST /api/auth/login` — đăng nhập
- `POST /api/auth/register` — đăng ký

- `GET  /api/doctors` — lấy danh sách bác sĩ
- `POST /api/doctors` — tạo bác sĩ

- `GET  /api/patients` — lấy danh sách bệnh nhân
- `GET  /api/patients/{id}` — lấy chi tiết bệnh nhân
- `POST /api/patients` — tạo bệnh nhân
- `PUT  /api/patients/{id}` — cập nhật bệnh nhân
- `DELETE /api/patients/{id}` — xóa bệnh nhân

- `GET  /api/appointments` — lấy lịch hẹn
- `POST /api/appointments` — tạo lịch hẹn
- `GET  /api/appointments/doctor/{doctorId}` — lấy lịch theo bác sĩ

- `GET  /api/prescriptions/patient/{patientId}` — lấy đơn thuốc theo bệnh nhân
- `POST /api/prescriptions` — tạo đơn thuốc

Lưu ý: README này liệt kê các route chính; tham khảo code trong `src/main/java/com/clinic/controller` để biết chi tiết request/response và tham số.

## Cài đặt & chạy
1. Build với Maven:

```bash
mvn clean package
```

2. Chạy ứng dụng (mặc định profile `dev` nếu cần chỉnh):

```bash
mvn spring-boot:run
# hoặc
java -jar target/your-artifact-name.jar
```

3. Sử dụng file cấu hình:
- `src/main/resources/application.yml`
- `src/main/resources/application-dev.yml`
- `src/main/resources/application-test.yml`

## Cấu hình môi trường
- Thiết lập datasource (URL, username, password) trong `application.yml` hoặc profile tương ứng.
- Các biến bảo mật (JWT secret, thời hạn) cấu hình trong file cấu hình hoặc biến môi trường tùy theo implement.

## Chạy kiểm thử & kiểm tra static
- Chạy test unit/integration bằng Maven:

```bash
mvn test
```

## Gợi ý phát triển
- Kiểm tra mã nguồn MapStruct trong `generated-sources/annotations` khi thay đổi mapper.
- Các exception tùy chỉnh nằm trong `com.clinic.exception`.

## Tác giả & Liên hệ
Dự án: Clinic (backend). Nếu cần tôi có thể:
- Tạo tài liệu API chi tiết (OpenAPI/Swagger)
- Viết hướng dẫn cấu hình DB và chạy bằng Docker
- Thêm ví dụ curl hoặc collection Postman

---
(cập nhật tự động dựa trên cấu trúc mã nguồn hiện có)
