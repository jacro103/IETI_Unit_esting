package org.adaschool.Weather.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class WeatherReportServiceTest {

    String API_KEY = "5f0707282965e71dd05c614dc2b1d311";
    String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    WeatherReportService weatherReportService;

    @Test
    void testGetWeatherReport() {
        double latitude = 37.8267;
        double longitude = -122.4233;
        String url = API_URL + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY;

        WeatherApiResponse weatherApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setHumidity(92);
        main.setTemperature(0);
        weatherApiResponse.setMain(main);


        WeatherReport report = new WeatherReport();
        report.setTemperature(main.getTemperature());
        report.setHumidity(main.getHumidity());


        when(restTemplate.getForObject(url, WeatherApiResponse.class)).thenReturn(weatherApiResponse);


        WeatherReport weatherReport = weatherReportService.getWeatherReport(latitude, longitude);


        assertEquals(report.getTemperature(), weatherReport.getTemperature());
        assertEquals(report.getHumidity(), weatherReport.getHumidity());
    }

}
