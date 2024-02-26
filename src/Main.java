import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {

        try {
            //URL da API do ChuckNorris
            String apiUrl = "https://api.chucknorris.io/jokes/random";

            //Fazendo a requisição GET para a API
            HttpURLConnection conexao = (HttpURLConnection) new URL(apiUrl).openConnection();
            conexao.setRequestMethod("GET");

            //Lendo a resposta da API
            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);

            }
            leitor.close();

            String piada = obterPiadaCuckNorris(resposta.toString());
            System.out.println("Piada: " + piada);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**

     *Método para extrair a piada do JSON retornado pela API.
     * @param resposta Resposta da API no for,ato String.
     * @return A piada extraida da resposta da API.
     */

    private static String obterPiadaCuckNorris(String resposta)  {


        //Extrair a piada do JSON
        int inicioDoIndice = resposta.indexOf("\"value\":") + ("\"value\":".length());
        int fimDoIndice = resposta.lastIndexOf("\"");
        return resposta.substring(inicioDoIndice, fimDoIndice);


    }


}
