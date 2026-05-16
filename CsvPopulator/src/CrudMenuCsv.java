// David Ferreira dos Santos
// Nicolas Rafael
// André Doria Santos
// Felipe Chagas Santos
// Marcus Vinicius
// Anderson Carneiro
//
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CrudMenuCsv {

    private static final String filePathUsuarios = "registros.csv";
    private static final String filePathEmpresas = "registros-empresas.csv";

    public static void main(String[] args) {
        while (true) {
            String[] options = { "Usuário", "Empresa", "Sair" };

            int choice = JOptionPane.showOptionDialog(
                null,
                "Escolha uma opção",
                "Menu CRUD",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
            );

            switch (choice) {
                case 0 -> usuarioRegistro();
                case 1 -> empresaRegistro();
                case 2 -> {
                    JOptionPane.showMessageDialog(
                        null,
                        "Encerrando o programa."
                    );

                    System.exit(0);
                }
                default -> JOptionPane.showMessageDialog(
                    null,
                    "Opção inválida."
                );
            }
        }
    }

    public static void usuarioRegistro() {
        while (true) {
            String[] options = {
                "Adicionar",
                "Listar",
                "Atualizar",
                "Remover",
                "Sair",
            };

            int choice = JOptionPane.showOptionDialog(
                null,
                "Escolha uma opção",
                "Menu CRUD",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
            );

            switch (choice) {
                case 0 -> adicionarRegistroUsuarios();
                case 1 -> listarRegistrosUsuarios();
                case 2 -> atualizarRegistroUsuarios();
                case 3 -> removerRegistroUsuarios();
                case 4 -> {
                    JOptionPane.showMessageDialog(
                        null,
                        "Encerrando o programa."
                    );

                    System.exit(0);
                }
                default -> JOptionPane.showMessageDialog(
                    null,
                    "Opção inválida."
                );
            }
        }
    }

    private static void adicionarRegistroUsuarios() {
        //Atualizações depois do feedback do Nicolas Massu
        String nome = JOptionPane.showInputDialog("Digite o nome:");
        if (nome.length() < 2) {
            JOptionPane.showMessageDialog(null, "Informe um nome válido.");
            return;
        }
        if ((nome.indexOf(" ") < 1) || (nome.lastIndexOf(" ") < 1)) {
            JOptionPane.showMessageDialog(null, "Informe seu nome completo.");
            return;
        }
        boolean testenumericoNome = nome.matches("[0-9]+");
        if (testenumericoNome == true) {
            JOptionPane.showMessageDialog(
                null,
                "Seu nome não pode conter números"
            );
            return;
        }
        String idade = JOptionPane.showInputDialog("Digite a idade:");
        boolean testenumericoIdade = idade.matches("[0-9]+");
        if (testenumericoIdade == false) {
            JOptionPane.showMessageDialog(null, "Informe uma idade válida.");
            return;
        }
        String cidade = JOptionPane.showInputDialog("Digite a cidade:");
        boolean testenumericoCidade = nome.matches("[0-9]+");
        if (testenumericoCidade == true) {
            JOptionPane.showMessageDialog(
                null,
                "Sua cidade não pode conter números."
            );
            return;
        }
        String email = JOptionPane.showInputDialog("Digite o email:");
        if (
            email.indexOf("@") == -1 ||
            (email.indexOf("@") != email.lastIndexOf("@"))
        ) {
            JOptionPane.showMessageDialog(null, "Email inválido!");
            return;
        }
        if (email.indexOf(".") == -1) {
            JOptionPane.showMessageDialog(null, "Email inválido!");
            return;
        }

        try (FileWriter writeruser = new FileWriter(filePathUsuarios, true)) {
            writeruser
                .append(nome.trim())
                .append(",")
                .append(idade.trim())
                .append(",")
                .append(cidade.trim())
                .append(",")
                .append(email.trim())
                .append("\n");
            JOptionPane.showMessageDialog(
                null,
                "Registro adicionado com sucesso!"
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar no arquivo");
            e.printStackTrace();
        }
    }

    private static void listarRegistrosUsuarios() {
        try (
            BufferedReader reader = new BufferedReader(
                new FileReader(filePathUsuarios)
            )
        ) {
            StringBuilder registrosUsuarios = new StringBuilder("Registros:\n");
            String linha;
            while ((linha = reader.readLine()) != null) {
                registrosUsuarios.append(linha).append("\n");
            }
            JOptionPane.showMessageDialog(null, registrosUsuarios.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }

    private static void atualizarRegistroUsuarios() {
        List<String> registrosUsuarios = lerRegistrosUsuarios();
        if (registrosUsuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado!");
            return;
        }

        String nomeBusca = JOptionPane.showInputDialog(
            "Digite o nome do registro a ser atualizado:"
        );
        boolean encontrado = false;
        for (int i = 0; i < registrosUsuarios.size(); i++) {
            String[] dados = registrosUsuarios.get(i).split(",");
            if (dados[0].equalsIgnoreCase(nomeBusca)) {
                String novoNome = JOptionPane.showInputDialog(
                    "Digite o novo nome:",
                    dados[0]
                );
                String novaIdade = JOptionPane.showInputDialog(
                    "Digite a nova idade:",
                    dados[1]
                );
                String novaCidade = JOptionPane.showInputDialog(
                    "Digite a nova cidade:",
                    dados[2]
                );
                String novoEmail = JOptionPane.showInputDialog(
                    "Digite o novo email:",
                    dados[3]
                );
                registrosUsuarios.set(
                    i,
                    novoNome +
                        "," +
                        novaIdade +
                        "," +
                        novaCidade +
                        "," +
                        novoEmail
                );
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            escreverRegistrosUsuarios(registrosUsuarios);
            JOptionPane.showMessageDialog(
                null,
                "Registro atualizado com sucesso!"
            );
        } else {
            JOptionPane.showMessageDialog(null, "Registro não encontrado.");
        }
    }

    private static void removerRegistroUsuarios() {
        List<String> registrosUsuarios = lerRegistrosUsuarios();
        if (registrosUsuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.");
            return;
        }

        String nomeBusca = JOptionPane.showInputDialog(
            "Digite o nome do registro a ser removido:"
        );
        boolean encontrado = registrosUsuarios.removeIf(registroUsuario ->
            registroUsuario.split(",")[0].equalsIgnoreCase(nomeBusca)
        );

        if (encontrado) {
            escreverRegistrosUsuarios(registrosUsuarios);
            JOptionPane.showMessageDialog(
                null,
                "Registro removido com sucesso!"
            );
        } else {
            JOptionPane.showMessageDialog(null, "Registro não encontrado.");
        }
    }

    private static List<String> lerRegistrosUsuarios() {
        List<String> registrosUsuarios = new ArrayList<>();
        try (
            BufferedReader reader = new BufferedReader(
                new FileReader(filePathUsuarios)
            )
        ) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                registrosUsuarios.add(linha);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo.");
            e.printStackTrace();
        }
        return registrosUsuarios;
    }

    private static void escreverRegistrosUsuarios(
        List<String> registrosUsuarios
    ) {
        try (FileWriter writer = new FileWriter(filePathUsuarios)) {
            for (String registroUsuario : registrosUsuarios) {
                writer.append(registroUsuario).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo.");
            e.printStackTrace();
        }
    }

    public static void empresaRegistro() {
        while (true) {
            String[] options = {
                "Adicionar",
                "Listar",
                "Atualizar",
                "Remover",
                "Sair",
            };

            int choice = JOptionPane.showOptionDialog(
                null,
                "Escolha uma opção",
                "Menu CRUD",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
            );

            switch (choice) {
                case 0 -> adicionarRegistroEmpresas();
                case 1 -> listarRegistrosEmpresas();
                case 2 -> atualizarRegistroEmpresas();
                case 3 -> removerRegistroEmpresas();
                case 4 -> {
                    JOptionPane.showMessageDialog(
                        null,
                        "Encerrando o programa."
                    );

                    System.exit(0);
                }
                default -> JOptionPane.showMessageDialog(
                    null,
                    "Opção inválida."
                );
            }
        }
    }

    private static void adicionarRegistroEmpresas() {
        String nome = JOptionPane.showInputDialog("Digite o nome da empresa:");
        if (nome.length() < 2) {
            JOptionPane.showMessageDialog(null, "Informe um nome válido.");
            return;
        }
        String testeSociedade = nome.toLowerCase();
        if (
            (testeSociedade.contains("ltda.") == false) &&
            (testeSociedade.contains("s/a") == false)
        ) {
            JOptionPane.showMessageDialog(
                null,
                "O nome da empresa deve conter 'Ltda.' ou 'S/A'"
            );
            return;
        }
        String cidade = JOptionPane.showInputDialog("Digite a cidade:");
        boolean testenumericoCidade = cidade.matches("[0-9]+");
        if (testenumericoCidade == true) {
            JOptionPane.showMessageDialog(
                null,
                "Sua cidade não pode conter números."
            );
            return;
        }
        String email = JOptionPane.showInputDialog("Digite o email:");
        if (
            email.indexOf("@") == -1 ||
            (email.indexOf("@") != email.lastIndexOf("@"))
        ) {
            JOptionPane.showMessageDialog(null, "Email inválido!");
            return;
        }
        if (email.indexOf(".") == -1) {
            JOptionPane.showMessageDialog(null, "Email inválido!");
            return;
        }
        String cnpj = JOptionPane.showInputDialog("Digite o CNPJ;");
        if (
            cnpj.indexOf("/") == -1 ||
            (cnpj.indexOf("/") != cnpj.lastIndexOf("/"))
        ) {
            JOptionPane.showMessageDialog(
                null,
                "CNPJ inválido! Digite no formato: 12.XXX.345/0000-99"
            );
            return;
        }
        if (
            cnpj.indexOf("-") == -1 ||
            cnpj.indexOf("-") != cnpj.lastIndexOf("-")
        ) {
            JOptionPane.showMessageDialog(
                null,
                "CNPJ inválido! Digite no formato: 12.XXX.345/0000-99" //máscara user friendly para cnpj
            );
            return;
        } else if (cnpj.trim().length() != 16) {
            JOptionPane.showMessageDialog(
                null,
                "CNPJ inválido. Digite no formato: 12.XXX.345/0000-99"
            );
            return;
        }

        try (FileWriter writeruser = new FileWriter(filePathEmpresas, true)) {
            writeruser
                .append(nome.trim())
                .append(",")
                .append(cidade.trim())
                .append(",")
                .append(email.trim())
                .append(",")
                .append(cnpj.trim())
                .append("\n");
            JOptionPane.showMessageDialog(
                null,
                "Registro adicionado com sucesso!"
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar no arquivo");
            e.printStackTrace();
        }
    }

    private static void listarRegistrosEmpresas() {
        try (
            BufferedReader reader = new BufferedReader(
                new FileReader(filePathEmpresas)
            )
        ) {
            StringBuilder registrosEmpresas = new StringBuilder("Registros:\n");
            String linha;
            while ((linha = reader.readLine()) != null) {
                registrosEmpresas.append(linha).append("\n");
            }
            JOptionPane.showMessageDialog(null, registrosEmpresas.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }

    private static void atualizarRegistroEmpresas() {
        List<String> registrosEmpresas = lerRegistrosEmpresas();
        if (registrosEmpresas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado!");
            return;
        }

        String nomeBusca = JOptionPane.showInputDialog(
            "Digite o nome do registro a ser atualizado:"
        );
        boolean encontrado = false;
        for (int i = 0; i < registrosEmpresas.size(); i++) {
            String[] dados = registrosEmpresas.get(i).split(",");
            if (dados[0].equalsIgnoreCase(nomeBusca)) {
                String novoNome = JOptionPane.showInputDialog(
                    "Digite o novo nome:",
                    dados[0]
                );
                String novaCidade = JOptionPane.showInputDialog(
                    "Digite a nova cidade:",
                    dados[1]
                );
                String novoEmail = JOptionPane.showInputDialog(
                    "Digite o novo email:",
                    dados[2]
                );
                String novoCnpj = JOptionPane.showInputDialog(
                    "Digite o novo CNPJ:",
                    dados[3]
                );
                registrosEmpresas.set(
                    i,
                    novoNome.trim() +
                        "," +
                        novaCidade.trim() +
                        "," +
                        novoEmail.trim() +
                        "," +
                        novoCnpj.trim()
                );
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            escreverRegistrosEmpresas(registrosEmpresas);
            JOptionPane.showMessageDialog(
                null,
                "Registro atualizado com sucesso!"
            );
        } else {
            JOptionPane.showMessageDialog(null, "Registro não encontrado.");
        }
    }

    private static void removerRegistroEmpresas() {
        List<String> registrosEmpresas = lerRegistrosEmpresas();
        if (registrosEmpresas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.");
            return;
        }

        String nomeBusca = JOptionPane.showInputDialog(
            "Digite o nome do registro a ser removido:"
        );
        boolean encontrado = registrosEmpresas.removeIf(registroEmpresa ->
            registroEmpresa.split(",")[0].equalsIgnoreCase(nomeBusca)
        );

        if (encontrado) {
            escreverRegistrosEmpresas(registrosEmpresas);
            JOptionPane.showMessageDialog(
                null,
                "Registro removido com sucesso!"
            );
        } else {
            JOptionPane.showMessageDialog(null, "Registro não encontrado.");
        }
    }

    private static List<String> lerRegistrosEmpresas() {
        List<String> registrosEmpresas = new ArrayList<>();
        try (
            BufferedReader reader = new BufferedReader(
                new FileReader(filePathEmpresas)
            )
        ) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                registrosEmpresas.add(linha);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo.");
            e.printStackTrace();
        }
        return registrosEmpresas;
    }

    private static void escreverRegistrosEmpresas(
        List<String> registrosEmpresas
    ) {
        try (FileWriter writer = new FileWriter(filePathEmpresas)) {
            for (String registroEmpresa : registrosEmpresas) {
                writer.append(registroEmpresa).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo.");
            e.printStackTrace();
        }
    }
}
