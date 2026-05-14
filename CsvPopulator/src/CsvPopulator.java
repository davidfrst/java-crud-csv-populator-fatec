import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.classfile.CodeBuilder.CatchBuilder;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;

public class CsvPopulator {

    private static final String filePath = "registros.csv";
    private static final String[] nomes = {
            "Marcelo", "Caio", "Bianca", "Amilton", "Tiago", "Raquel", "Rodrigo",
            "Matheus", "Carolina", "Vitória", "Amanda", "Nicolas", "Júlia", "André",
            "Marcus", "Rafael", "Rafaela", "Fernanda", "Paula", "Larissa", "Thaís",
            "Jéssica", "Juliana", "Beatriz", "Diego", "Anderson", "Patrícia", "Miguel",
            "Luíza", "Gabriel", "Eduardo", "Eduarda", "Laura", "Sofia", "Camila", "David",
            "Bernardo", "Vinícius", "Guilherme", "Felipe", "Adriana", "Elaine", "Lucas", "Igor",
            "Manuela", "Giovana", "Joaquim", "Natália", "Aline", "Gustavo", "Helena"
    };
    private static final String[] sobreNomes = {
            "Moraes", "Mendes", "Duarte", "Queiroz", "Andrade", "Martins", "Rodrigues", "Cardoso",
            "Carvalho", "Fonseca", "Marques", "Batista", "Peixoto", "Figueiredo", "Machado", "Melo",
            "Pinto", "Ramos", "Ribeiro", "Dias", "Pereira", "Cavalcanti", "Freitas", "Costela", "Guerra",
            "Almeida", "Cunha", "Nunes", "Ferreira", "Moreira", "Pimentel", "Barbosa", "Miranda", "Gonçalves",
            "Azevedo", "Castro", "Monteiro", "Barroso", "Henriques", "Rocha", "Lopes", "Gomes", "Neves",
            "Nascimento", "Fernandes", "Lima", "Costa", "Nogueira", "Oliveira", "Barros"
    };
    private static final String[] emails = {
            "@gmail.com", "@hotmail.com", "@outlook.com", "@yahoo.com", "@icloud.com", "@fatec.sp.gov.br",
            "@mail.com"
    };
    private static final String[] cidades = {
            "Itapecerica da Serra", "Barueri", "Franco da Rocha", "Suzano", "Itu", "Presidente Prudente",
            "Mogi das Cruzes", "São Paulo", "Franca", "São José do Rio Preto", "Carapicuíba", "Jandira",
            "São Bernardo do Campo", "Guarulhos", "Sumaré", "Sorocaba", "Hortolândia", "Embu das Artes",
            "Piracicaba", "Bauru", "Campinas", "Araraquara", "Rio Claro", "Ribeirão Pires", "Osasco", "Diadema",
            "Limeira", "Indaiatuba", "Taboão da Serra", "Ribeirão Preto", "São Carlos", "Santo André",
            "São José dos Campos", "Mauá", "Jundiaí", "São Caetano do Sul"
    };

    public static void popularArquivoCsv() {
            Random random = new Random();
    
            try (FileWriter writer = new FileWriter(filePath, true)) {
                for (int i = 0; i < 50; i++) {
                    String nome = nomes[random.nextInt(nomes.length)];
                    String sobrenome = sobreNomes[random.nextInt(sobreNomes.length)];
                    String nomecompleto = (nome + " " + sobrenome);
                    String email = emails[random.nextInt(emails.length)];
                    String emailpessoal = (nome + "." + sobrenome).toLowerCase() + email;
                    emailpessoal = emailpessoal.replaceAll("á|ã","a").replaceAll("é|ê","e").replaceAll("í","i").replaceAll("ó","o").replaceAll("ú","u").replaceAll("ç","c");
                    int idade = random.nextInt(43) + 18; // Idade entre 18 e 60
                    String cidade = cidades[random.nextInt(cidades.length)];

                    writer.append(nomecompleto).append(",")
                          .append(String.valueOf(idade)).append(",")
                          .append(cidade).append(",")
                          .append(emailpessoal).append("\n");
                }
                JOptionPane.showMessageDialog(null,"50 registros aleatórios adicionados com sucesso!");
            }   catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao gravar o arquivo.");
                e.printStackTrace();}
            }

            public static void main(String[] args) {
                popularArquivoCsv();
    }
}
