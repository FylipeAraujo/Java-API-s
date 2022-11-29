import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //Conexão com site IMDB para os top250 filmes (conexão HTTP)

        String url="https://imdb-api.com/en/API/Title/k_4eh6bte3/tt1832382";
        URI endereco=URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request , BodyHandlers.ofString());
        String body= response.body();
        System.out.println(body);

        //Extrair dados  dos 250 filmes.(nome, poster, classificação)
        var Parse = new JsonParse();
        List<Map<String,String>>listaDeFilmes= Parse.parse(body);

        //Exibir e manipulação de dados

        for (Map<String,String>filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating")+"\n");
            
        }
    }
}
