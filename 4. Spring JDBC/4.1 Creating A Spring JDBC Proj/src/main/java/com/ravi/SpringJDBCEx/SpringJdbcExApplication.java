package com.ravi.SpringJDBCEx;

import com.ravi.SpringJDBCEx.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringJdbcExApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJdbcExApplication.class, args);

		Student st = context.getBean(Student.class);
		st.setRollNo(101);
		st.setName("Ravi");
		st.setMarks(99);

		addStudent(s);
	}

}
