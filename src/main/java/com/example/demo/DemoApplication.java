package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.time.*;
import java.util.*;

@Slf4j
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private final static String FILE= "C:\\Users\\sparau\\Desktop\\github-ranking-2022-02-26.csv";
	private final static String cvsSplitBy = ",";
	String topN ="0";
	String topC ="";
	boolean validInputs;



	@Override
	public void run(String... args) throws Exception {



		while (!validInputs) {
			interactionUser();

			//Comprobamos que los datos introducidos son los esperados
			if (!Strings.isEmpty(topC) && StringUtils.isNumeric(topN)){
				validInputs = true;

			}else{
				System.out.println("TopN deben ser números y topC deben ser caracteres");
			}
		}


		exportCsv();
	}


	private void interactionUser(){
// Enter data using Scanner
		Scanner in = new Scanner(System.in);

		//Se introduce el número de filas que se quiere obtener
		System.out.println("Introduzca el número de filas que quiere mostrar");
		 topN = in.nextLine();

		//Nos devuelve el lenguage que se quiere obtener
		System.out.println("Introduzca el lenguaje que quiere mostrar");
		topC = in.nextLine();



	}

	private boolean checktopNifCompleted(int increment){
		return Integer.parseInt(topN) == increment;

	}

	private void exportCsv () {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE));) {
			List<String> result = new ArrayList<>();
			int increment = 0;
			String bline;

			while ((bline = bufferedReader.readLine()) != null) {

				String[] splitedCsvData = bline.split(cvsSplitBy);

				if ((splitedCsvData[1].toUpperCase().trim().equals(topC.toUpperCase().trim()) && Integer.parseInt(topN) >= Integer.parseInt(splitedCsvData[0]))) {
					result.add(bline);
					System.out.println(bline + "\n");
					increment++;
				}

				if (checktopNifCompleted(increment)) {
					break;
				}


			}


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
