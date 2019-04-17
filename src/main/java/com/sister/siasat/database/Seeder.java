package com.sister.siasat.database;

import com.github.javafaker.Faker;
import com.sister.siasat.database.repository.DepartmentRepository;
import com.sister.siasat.model.Gender;
import com.sister.siasat.model.entity.Department;
import com.sister.siasat.model.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Configuration
@Slf4j
public class Seeder {

    @Bean
    CommandLineRunner initDataBase(DepartmentRepository repository) {
        List<Student> students1 = new ArrayList<>();
        List<Student> students2 = new ArrayList<>();
        List<Student> students3 = new ArrayList<>();
        Random random = new Random();
        List<Gender> genders = List.of(Gender.values());
        for (int i = 1; i <= 150; i++) {
            Faker faker = new Faker(new Locale("in-ID"));
            students1.add(new Student(
                    (672016000 + i),
                    faker.name().fullName(),
                    genders.get(random.nextInt(genders.size())),
                    faker.date().birthday(21, 22).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            ));
        }
        for (int i = 1; i <= 120; i++) {
            Faker faker = new Faker(new Locale("in-ID"));
            students2.add(new Student(
                    (682016000 + i),
                    faker.name().fullName(),
                    genders.get(random.nextInt(genders.size())),
                    faker.date().birthday(21, 22).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            ));
        }
        for (int i = 1; i <= 90; i++) {
            Faker faker = new Faker(new Locale("in-ID"));
            students3.add(new Student(
                    (702016000 + i),
                    faker.name().fullName(),
                    genders.get(random.nextInt(genders.size())),
                    faker.date().birthday(21, 22).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            ));
        }
        return args -> {
            log.info(repository.save(new Department(67, "Teknik Informatika", students1)).getName());
            log.info(repository.save(new Department(68, "Sistem Informasi", students2)).getName());
            log.info(repository.save(new Department(70, "Pendidikan Teknik Informatika & Komputer", students3)).getName());
        };
    }

}
