import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.classfile.CodeBuilder.CatchBuilder;
import java.util.Random;
import javax.swing.JOptionPane;

public class CsvPopulator {

    private static final String filePath = "registros.csv";
    private static final String[] nomes = {
            "Adriana", "Nicolas", "Aline", "Amanda", "André", "Beatriz",
            "Bernardo", "Bianca", "Caio", "Camila", "Carolina", "David",
            "Diego", "Eduarda", "Eduardo", "Elaine", "Marcus", "Felipe",
            "Fernanda", "Gabriel", "Giovana", "Guilherme", "Gustavo", "Helena",
            "Igor", "Jéssica", "Joaquim", "Júlia", "Juliana", "Larissa",
            "Laura", "Lucas", "Luíza", "Manuela", "Marcelo", "Matheus",
            "Miguel", "Natália", "André", "Patrícia", "Paula", "Rafael",
            "Rafaela", "Raquel", "Anderson", "Rodrigo", "Sofia", "Thaís",
            "Tiago", "Vinícius", "Vitória"
    };
    private static final String[] sobreNomes = {
            "Almeida", "Andrade", "Azevedo", "Barbosa", "Barros", "Barroso",
            "Batista", "Cardoso", "Carvalho", "Castro", "Cavalcanti", "Costa",
            "Costela", "Cunha", "Dias", "Duarte", "Fernandes", "Ferreira",
            "Figueiredo", "Fonseca", "Freitas", "Gomes", "Gonçalves", "Guerra",
            "Henriques", "Lima", "Lopes", "Machado", "Marques", "Martins",
            "Melo", "Mendes", "Miranda", "Monteiro", "Moraes", "Moreira",
            "Nascimento", "Neves", "Nogueira", "Nunes", "Oliveira", "Peixoto",
            "Pereira", "Pimentel", "Pinto", "Queiroz", "Ramos", "Ribeiro",
            "Rocha", "Rodrigues"
    };
    private static final String[] emails = {
            "@gmail.com", "@hotmail.com", "@outlook.com", "@yahoo.com", "@icloud.com", "@fatec.sp.gov.br",
            "@live.com", "@protonmail.com", "@ymail.com", "@mail.com", "@inbox.com", "@fastmail.com"
    };
    private static final String[] cidades = {
            "Barueri", "Carapicuíba", "Diadema", "Embu das Artes", "Franco da Rocha", "Guarulhos",
            "Itapecerica da Serra", "Jandira", "Mauá", "Mogi das Cruzes", "Osasco", "Ribeirão Pires",
            "Santo André", "São Bernardo do Campo", "São Caetano do Sul", "São Paulo", "Suzano",
            "Taboão da Serra", "Araraquara", "Bauru", "Campinas", "Franca", "Hortolândia",
            "Indaiatuba", "Itu", "Jundiaí", "Limeira",
            "Piracicaba", "Presidente Prudente", "Ribeirão Preto", "Rio Claro",
            "Sumaré", "São Carlos", "São José do Rio Preto", "São José dos Campos", "Sorocaba"
    };

    public static void popularArquivoCsv() {
            Random random = new Random();
    
            try (FileWriter writer = new FileWriter(filePath, true)) {
                for (int i = 0; i < 100; i++) {
                    String nome = nomes[random.nextInt(nomes.length)];
                    String sobrenome = sobreNomes[random.nextInt(sobreNomes.length)];
                    String nomecompleto = (nome + " " + sobrenome);
                    String email = emails[random.nextInt(emails.length)];
                    String emailpessoal = (nome + sobrenome).toLowerCase() + email;
                    int idade = random.nextInt(43) + 18; // Idade entre 18 e 60
                    String cidade = cidades[random.nextInt(cidades.length)];

                    writer.append(nomecompleto).append(",")
                          .append(String.valueOf(idade)).append(",")
                          .append(emailpessoal).append(",")
                          .append(cidade).append("\n");
                }
                JOptionPane.showMessageDialog(null,"100 registros aleatórios adicionados com sucesso!");
            }   catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao gravar o arquivo.");
                e.printStackTrace();}
            }

            public static void main(String[] args) {
                popularArquivoCsv();
    }
}
