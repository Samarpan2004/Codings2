import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import org.json.JSONObject;

public class WeatherApp extends JFrame {
    private JTextField cityInput;
    private JButton fetchButton;
    private JTextArea resultArea;

    private final String API_KEY = "YOUR_API_KEY"; // Replace with your OpenWeatherMap API key

    public WeatherApp() {
        setTitle("Weather Information System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // North Panel (input)
        JPanel inputPanel = new JPanel();
        cityInput = new JTextField(20);
        fetchButton = new JButton("Get Weather");
        inputPanel.add(new JLabel("Enter City:"));
        inputPanel.add(cityInput);
        inputPanel.add(fetchButton);

        // Center Panel (result)
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Add panels
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Event
        fetchButton.addActionListener(e -> fetchWeather());

        setVisible(true);
    }

    private void fetchWeather() {
        String city = cityInput.getText().trim();
        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a city name.");
            return;
        }

        try {
            String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + 
                             URLEncoder.encode(city, "UTF-8") + 
                             "&appid=" + API_KEY + "&units=metric";

            HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();

            parseWeather(response.toString());
        } catch (Exception ex) {
            resultArea.setText("Error fetching weather data:\n" + ex.getMessage());
        }
    }

    private void parseWeather(String jsonResponse) {
        try {
            JSONObject obj = new JSONObject(jsonResponse);
            JSONObject main = obj.getJSONObject("main");
            JSONObject weather = obj.getJSONArray("weather").getJSONObject(0);
            JSONObject wind = obj.getJSONObject("wind");

            StringBuilder sb = new StringBuilder();
            sb.append("City: ").append(obj.getString("name")).append("\n");
            sb.append("Temperature: ").append(main.getDouble("temp")).append(" °C\n");
            sb.append("Humidity: ").append(main.getInt("humidity")).append(" %\n");
            sb.append("Weather: ").append(weather.getString("main")).append(" - ")
              .append(weather.getString("description")).append("\n");
            sb.append("Wind Speed: ").append(wind.getDouble("speed")).append(" m/s\n");

            resultArea.setText(sb.toString());
        } catch (Exception e) {
            resultArea.setText("Error parsing weather data:\n" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WeatherApp::new);
    }
}
