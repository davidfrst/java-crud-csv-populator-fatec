import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.classfile.CodeBuilder.CatchBuilder;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import java.text.Normalizer;

public class CsvPopulatorEnterprise {

    private static final String filePath = "registros-empresas.csv";
    private static final String[] nomes = {
        "Bio", "Pharma", "Auto", "Agro", "Pets", "Health", "Food", "Info", "Eco"
    };
    private static final String[] subnomes = {
        "Tech", "New", "Clean", "Med", "Smart", "Mood", "Nova", "Next", "Solutions", "Servers", "Works"
    };
    private static final String[] cidades = {
        "Itapecerica da Serra", "Barueri", "Franco da Rocha", "Suzano", "Itu", "Presidente Prudente",
        "Mogi das Cruzes", "São Paulo", "Franca", "São José do Rio Preto", "Carapicuíba", "Jandira",
        "São Bernardo do Campo", "Guarulhos", "Sumaré", "Sorocaba", "Hortolândia", "Embu das Artes",
        "Piracicaba", "Bauru", "Campinas", "Araraquara", "Rio Claro", "Ribeirão Pires", "Osasco", "Diadema",
        "Limeira", "Indaiatuba", "Taboão da Serra", "Ribeirão Preto", "São Carlos", "Santo André",
        "São José dos Campos", "Mauá", "Jundiaí", "São Caetano do Sul"
    };
    private static final String[] alfanumerico = {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
        "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };
    private static final String[] digito = {
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    public static void popularArquivoCsv() {
        Random random = new Random();

        try (FileWriter writer = new FileWriter(filePath, true)) {
            for (int i = 0; i < 10; i++) {
                String nome = nomes[random.nextInt(nomes.length)];
                String subnome = subnomes[random.nextInt(subnomes.length)];
                String nomeempresa = (nome + subnome);
                String empresalimitada = (nomeempresa + " Ltda.");
                String cidade = cidades[random.nextInt(cidades.length)];
                String email = ("enterprise" + "." + cidade.toLowerCase() + "@" + nomeempresa.toLowerCase() + ".com");
                email = email.replaceAll("\s+","");
                email = email.replaceAll("á|ã","a").replaceAll("é|ê","e").replaceAll("í","i").replaceAll("ó","o").replaceAll("ú","u").replaceAll("ç","c");
                String cnpj = "";
                for (int x = 0; x <= 11; x++) {
                    if (x==8){
                        cnpj += "/";
                    }
                    cnpj += alfanumerico[random.nextInt(alfanumerico.length)];
                }
                for (int y = 0; y <= 1; y++) {
                    if (y==0){
                        cnpj += "-";
                    }
                    cnpj += digito[random.nextInt(digito.length)];
                }
                
                writer
                    .append(empresalimitada).append(",")
                    .append(cidade).append(",")
                    .append(email).append(",")
                    .append(cnpj).append("\n");
            }
            JOptionPane.showMessageDialog(
                null,
                "10 registros aleatórios adicionados com sucesso!"
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar o arquivo.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        popularArquivoCsv();
    }
}
