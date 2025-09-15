import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PetSystem {

    private final Scanner scanner;

    public PetSystem() {
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        exibirMenuPrincipal();
    }

    private void lerEExibirFormulario() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/resources/formulario.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("==== BEM-VINDO AO SISTEMA DE CADASTRO ====");
            System.out.println("1 - Iniciar o sistema para cadastro de PETS");
            System.out.println("2 - Iniciar o sistema para alterar formulário");
            System.out.println("3 - Sair");

            try {
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        exibirMenuPet();
                        break;
                    case 2:
                        exibirMenuFormulario();
                        break;
                    case 3:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
                scanner.nextLine();
                opcao = 0;
            }
        } while (opcao != 3);
        scanner.close();
    }

    private void exibirMenuPet() {

        int opcao;
        do {
            System.out.println("==== MENU DE CADASTRO DE PETS ====");
            System.out.println("1 - Cadastrar um novo pet");
            System.out.println("2 - Alterar dados do pet");
            System.out.println("3 - Deletar um pet cadastrado");
            System.out.println("4 - Listar todos os pets cadastrados");
            System.out.println("5 - Listar pets por algum critério");
            System.out.println("6 - Voltar ao menu principal");

            try {
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarNovoPet();
                        break;
                    case 2:
                        alterarPet();
                        break;
                    case 3:
                        deletarPet();
                        break;
                    case 4:
                        listarTodosPets();
                        break;
                    case 5:
                        buscarPet();
                        break;
                    case 6:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
                scanner.nextLine();
                opcao = 0;
            }
        } while (opcao != 6);
    }

    private void cadastrarNovoPet() {
        System.out.println("--- CADASTRO DE NOVO PET ---");

        System.out.print("1 - Qual o nome e sobrenome do pet?: ");
        String nomePet = scanner.nextLine();
        if (nomePet.trim().isEmpty()) {
            System.out.println("O nome do pet não pode ser vazio.");
            return;
        }

        Pet.Tipo tipoDoPet = null;
        while (tipoDoPet == null) {
            System.out.print("2 - Qual o tipo do pet (Cachorro/Gato)?: ");
            String tipoString = scanner.nextLine();
            try {
                tipoDoPet = Pet.Tipo.valueOf(tipoString.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo inválido. Por favor, escolha entre Cachorro ou Gato.");
            }
        }

        Pet.Sexo sexoPet = null;
        while (sexoPet == null) {
            System.out.print("3 - Qual o sexo do animal (Macho/Fêmea)?: ");
            String sexoString = scanner.nextLine();
            try {
                sexoPet = Pet.Sexo.valueOf(sexoString.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Sexo inválido. Por favor, escolha entre Macho ou Fêmea.");
            }
        }

        String enderecoBairro;
        while (true) {
            System.out.print("4 - Qual o endereço e bairro que ele foi encontrado?: ");
            enderecoBairro = scanner.nextLine();
            if (!enderecoBairro.trim().isEmpty()) {
                break;
            }
            System.out.println("O endereço do pet não pode ser vazio. Por favor, tente novamente.");
        }

        int idadePet = 0;
        while (true) {
            System.out.print("5 - Qual a idade aproximada do pet?(em anos): ");
            String idadeString = scanner.nextLine();
            try {
                int idadeAnos = Integer.parseInt(idadeString);
                if (idadeAnos > 20) {
                    System.out.println("A idade não pode ser maior que 20 anos. Por favor, tente novamente.");
                } else if (idadeAnos < 1) {
                    System.out.print("Idade menor que 1 ano. Por favor, digite a idade (em meses): ");
                    String mesesString = scanner.nextLine();
                    int meses = Integer.parseInt(mesesString);
                    idadePet = (int) Math.round((double) meses / 12);
                    break;
                } else {
                    idadePet = idadeAnos;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite apenas números.");
            }
        }

        double pesoPet = 0.0;
        while (true) {
            System.out.print("6 - Qual o peso aproximado do pet?(em kg): ");
            String pesoString = scanner.nextLine();
            try {
                double pesoAnimal = Double.parseDouble(pesoString);
                if (pesoAnimal > 60) {
                    System.out.println("Peso inválido. O peso não pode ser maior que 60kg");
                } else if (pesoAnimal < 0.5) {
                    System.out.println("Peso inválido. O peso não pode ser menor que 0.5kg");
                } else {
                    pesoPet = pesoAnimal;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite apenas números.");
            }
        }

        String racaPet = null;
        while (true) {
            System.out.print("7 - Qual a raça do pet?: ");
            racaPet = scanner.nextLine();
            if (racaPet.trim().isEmpty()) {
                System.out.println("A raça não pode ser vazia. Por favor, tente novamente.");
            } else if (racaPet.matches("[a-zA-Z\\s]+")) {
                break;
            } else {
                System.out.println("Entrada inválida. A raça não pode conter números ou caracteres especiais.");
            }
        }

        System.out.println("Pet cadastrado com sucesso!");
        Pet novoPet = new Pet(nomePet, tipoDoPet, sexoPet, enderecoBairro, idadePet, pesoPet, racaPet);

        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataFormatada = agora.format(formatador);
        String nomeParaArquivo = novoPet.getNomeCompleto().trim().toUpperCase().replace(" ", "");
        String nomeArquivo = dataFormatada + "-" + nomeParaArquivo + ".txt";

        System.out.println("Nome do arquivo gerado: " + nomeArquivo);
        System.out.println("-----------------------------------------");

        String nomePasta = "petsCadastrados";
        File pasta = new File(nomePasta);
        if (!pasta.exists()) {
            if (pasta.mkdirs()) {
                System.out.println("Pasta 'petsCadastrados' criada com sucesso!");
            } else {
                System.err.println("Erro ao criar a pasta 'petsCadastrados'.");
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pasta.getAbsolutePath() + File.separator + nomeArquivo))) {
            writer.write("1 - " + novoPet.getNomeCompleto() + "\n");
            writer.write("2 - " + novoPet.getTipo() + "\n");
            writer.write("3 - " + novoPet.getSexo() + "\n");
            writer.write("4 - " + novoPet.getEnderecoBairro() + "\n");
            writer.write("5 - " + novoPet.getIdade() + "\n");
            writer.write("6 - " + novoPet.getPeso() + "\n");
            writer.write("7 - " + novoPet.getRaca() + "\n");

            System.out.println("Dados do pet salvos com sucesso em: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    private void buscarPet() {
        System.out.println("\n--- BUSCA DE PET ---");

        File pastaPets = new File("petsCadastrados");
        if (!pastaPets.exists() || !pastaPets.isDirectory()) {
            System.out.println("Nenhum pet cadastrado encontrado.");
            return;
        }

        File[] arquivosPets = pastaPets.listFiles();
        if (arquivosPets == null || arquivosPets.length == 0) {
            System.out.println("Nenhum pet cadastrado encontrado.");
            return;
        }

        System.out.println("Por qual critério você deseja buscar?");
        System.out.println("1 - Nome | 2 - Tipo | 3 - Sexo | 4 - Endereço | 5 - Idade | 6 - Peso | 7 - Raça");
        System.out.print("Escolha uma opção: ");
        int criterio = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o termo de busca: ");
        String termoBusca = scanner.nextLine();

        System.out.println("\n--- Resultados da Busca ---");

        boolean petEncontrado = false;

        for (File arquivo : arquivosPets) {
            Pet pet = lerPetDoArquivo(arquivo);
            if (pet != null) {
                boolean corresponde = false;
                switch (criterio) {
                    case 1:
                        corresponde = pet.getNomeCompleto().toLowerCase().contains(termoBusca.toLowerCase());
                        break;
                    case 2:
                        corresponde = pet.getTipo().toString().equalsIgnoreCase(termoBusca);
                        break;
                    case 3:
                        corresponde = pet.getSexo().toString().equalsIgnoreCase(termoBusca);
                        break;
                    case 4:
                        corresponde = pet.getEnderecoBairro().toLowerCase().contains(termoBusca.toLowerCase());
                        break;
                    case 5:
                        try {
                            int idadeBusca = Integer.parseInt(termoBusca);
                            corresponde = pet.getIdade() == idadeBusca;
                        } catch (NumberFormatException e) {
                            System.out.println("Termo de busca para idade inválido.");
                        }
                        break;
                    case 6:
                        try {
                            double pesoBusca = Double.parseDouble(termoBusca);
                            corresponde = pet.getPeso() == pesoBusca;
                        } catch (NumberFormatException e) {
                            System.out.println("Termo de busca para peso inválido.");
                        }
                        break;
                    case 7:
                        corresponde = pet.getRaca().toLowerCase().contains(termoBusca.toLowerCase());
                        break;
                    default:
                        System.out.println("Opção de busca inválida.");
                }
                if (corresponde) {
                    exibirDetalhesDoPet(pet);
                    petEncontrado = true;
                }
            }
        }

        if (!petEncontrado) {
            System.out.println("Nenhum pet encontrado com o critério informado.");
        }
    }

    private void exibirDetalhesDoPet(Pet pet) {
        System.out.println("\n-----------------------------------------");
        System.out.println("Nome: " + pet.getNomeCompleto());
        System.out.println("Tipo: " + pet.getTipo());
        System.out.println("Sexo: " + pet.getSexo());
        System.out.println("Endereço: " + pet.getEnderecoBairro());
        System.out.println("Idade: " + pet.getIdade());
        System.out.println("Peso: " + pet.getPeso());
        System.out.println("Raça: " + pet.getRaca());
        System.out.println("-----------------------------------------");
    }

    private Pet lerPetDoArquivo(File arquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String nomeCompleto = reader.readLine().substring(4).trim();
            String tipoString = reader.readLine().substring(4).trim();
            String sexoString = reader.readLine().substring(4).trim();
            String enderecoBairro = reader.readLine().substring(4).trim();
            String idadeString = reader.readLine().substring(4).trim();
            String pesoString = reader.readLine().substring(4).trim();
            String raca = reader.readLine().substring(4).trim();

            Pet.Tipo tipo = Pet.Tipo.valueOf(tipoString.toUpperCase());
            Pet.Sexo sexo = Pet.Sexo.valueOf(sexoString.toUpperCase());
            int idade = Integer.parseInt(idadeString);
            double peso = Double.parseDouble(pesoString);

            return new Pet(nomeCompleto, tipo, sexo, enderecoBairro, idade, peso, raca);
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Erro ao ler ou converter o arquivo " + arquivo.getName() + ": " + e.getMessage());
            return null;
        }
    }

    private List<File> encontrarPetsPorCriterio() {
        System.out.println("\n--- BUSCA DE PET PARA ALTERAÇÃO ---");

        File pastaPets = new File("petsCadastrados");
        if (!pastaPets.exists() || !pastaPets.isDirectory()) {
            System.out.println("Nenhum pet cadastrado encontrado.");
            return new ArrayList<>();
        }

        File[] arquivosPets = pastaPets.listFiles();
        if (arquivosPets == null || arquivosPets.length == 0) {
            System.out.println("Nenhum pet cadastrado encontrado.");
            return new ArrayList<>();
        }

        System.out.println("Por qual critério você deseja buscar?");
        System.out.println("1 - Nome | 2 - Tipo | 3 - Sexo | 4 - Endereço | 5 - Idade | 6 - Peso | 7 - Raça");
        System.out.print("Escolha uma opção: ");
        int criterio = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o termo de busca: ");
        String termoBusca = scanner.nextLine();

        List<File> resultados = new ArrayList<>();

        for (File arquivo : arquivosPets) {
            Pet pet = lerPetDoArquivo(arquivo);
            if (pet != null) {
                boolean corresponde = false;
                switch (criterio) {
                    case 1:
                        corresponde = pet.getNomeCompleto().toLowerCase().contains(termoBusca.toLowerCase());
                        break;
                    case 2:
                        corresponde = pet.getTipo().toString().equalsIgnoreCase(termoBusca);
                        break;
                    case 3:
                        corresponde = pet.getSexo().toString().equalsIgnoreCase(termoBusca);
                        break;
                    case 4:
                        corresponde = pet.getEnderecoBairro().toLowerCase().contains(termoBusca.toLowerCase());
                        break;
                    case 5:
                        try {
                            int idadeBusca = Integer.parseInt(termoBusca);
                            corresponde = pet.getIdade() == idadeBusca;
                        } catch (NumberFormatException e) {
                            System.out.println("Termo de busca para idade inválido.");
                        }
                        break;
                    case 6:
                        try {
                            double pesoBusca = Double.parseDouble(termoBusca);
                            corresponde = pet.getPeso() == pesoBusca;
                        } catch (NumberFormatException e) {
                            System.out.println("Termo de busca para peso inválido.");
                        }
                        break;
                    case 7:
                        corresponde = pet.getRaca().toLowerCase().contains(termoBusca.toLowerCase());
                        break;
                }
                if (corresponde) {
                    resultados.add(arquivo);
                }
            }
        }
        return resultados;
    }

    public void alterarPet() {
        List<File> resultados = encontrarPetsPorCriterio();

        if (resultados.isEmpty()) {
            System.out.println("Nenhum pet encontrado para alteração.");
            return;
        }

        System.out.println("\nPets encontrados:");
        for (int i = 0; i < resultados.size(); i++) {
            File arquivo = resultados.get(i);
            Pet pet = lerPetDoArquivo(arquivo);
            System.out.println((i + 1) + ". " + pet.getNomeCompleto() + " - " + pet.getTipo());
        }

        System.out.print("\nDigite o número do pet que deseja alterar: ");
        try {
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha > 0 && escolha <= resultados.size()) {
                File arquivoParaAlterar = resultados.get(escolha - 1);
                Pet petOriginal = lerPetDoArquivo(arquivoParaAlterar);

                System.out.println("\nAlterando dados de: " + petOriginal.getNomeCompleto());

                // Coleta de novos dados, exceto tipo e sexo
                // Os valores atuais são mostrados para referência

                // 1. Nome e sobrenome
                System.out.print("Novo nome e sobrenome (atual: " + petOriginal.getNomeCompleto() + "): ");
                String novoNome = scanner.nextLine();
                if (novoNome.trim().isEmpty()) {
                    System.out.println("Nome não pode ser vazio. Usando o nome original.");
                    novoNome = petOriginal.getNomeCompleto();
                }

                // 2. Endereço e bairro
                System.out.print("Novo endereço e bairro (atual: " + petOriginal.getEnderecoBairro() + "): ");
                String novoEnderecoBairro = scanner.nextLine();
                if (novoEnderecoBairro.trim().isEmpty()) {
                    System.out.println("Endereço não pode ser vazio. Usando o endereço original.");
                    novoEnderecoBairro = petOriginal.getEnderecoBairro();
                }

                // 3. Idade
                int novaIdade = 0;
                while (true) {
                    System.out.print("Nova idade (atual: " + petOriginal.getIdade() + " anos): ");
                    String idadeString = scanner.nextLine();
                    if (idadeString.trim().isEmpty()) {
                        novaIdade = petOriginal.getIdade();
                        System.out.println("Usando a idade original.");
                        break;
                    }
                    try {
                        novaIdade = Integer.parseInt(idadeString);
                        if (novaIdade > 20 || novaIdade < 0) {
                            System.out.println("Idade inválida. A idade deve ser entre 0 e 20.");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, digite apenas números.");
                    }
                }

                // 4. Peso
                double novoPeso = 0.0;
                while (true) {
                    System.out.print("Novo peso (atual: " + petOriginal.getPeso() + " kg): ");
                    String pesoString = scanner.nextLine();
                    if (pesoString.trim().isEmpty()) {
                        novoPeso = petOriginal.getPeso();
                        System.out.println("Usando o peso original.");
                        break;
                    }
                    try {
                        novoPeso = Double.parseDouble(pesoString);
                        if (novoPeso > 60 || novoPeso < 0.5) {
                            System.out.println("Peso inválido. O peso deve ser entre 0.5kg e 60kg.");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, digite apenas números.");
                    }
                }

                // 5. Raça
                System.out.print("Nova raça (atual: " + petOriginal.getRaca() + "): ");
                String novaRaca = scanner.nextLine();
                if (novaRaca.trim().isEmpty()) {
                    System.out.println("Raça não pode ser vazia. Usando a raça original.");
                    novaRaca = petOriginal.getRaca();
                }

                // AQUI VOCÊ DELETA O ARQUIVO ANTIGO
                if (arquivoParaAlterar.delete()) {
                    System.out.println("Arquivo antigo do pet deletado com sucesso.");
                } else {
                    System.out.println("Erro ao deletar o arquivo antigo.");
                    return;
                }

                // AQUI VOCÊ CRIA O NOVO OBJETO COM AS INFORMAÇÕES ATUALIZADAS
                Pet petAtualizado = new Pet(novoNome, petOriginal.getTipo(), petOriginal.getSexo(), novoEnderecoBairro, novaIdade, novoPeso, novaRaca);

                // AQUI VOCÊ SALVA O NOVO OBJETO EM UM NOVO ARQUIVO
                LocalDateTime agora = LocalDateTime.now();
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
                String dataFormatada = agora.format(formatador);
                String nomeParaArquivo = petAtualizado.getNomeCompleto().trim().toUpperCase().replace(" ", "");
                String nomeArquivo = dataFormatada + "-" + nomeParaArquivo + ".txt";

                String nomePasta = "petsCadastrados";
                File pasta = new File(nomePasta);
                if (!pasta.exists() && pasta.mkdirs()) {
                    System.out.println("Pasta 'petsCadastrados' criada com sucesso!");
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(pasta.getAbsolutePath() + File.separator + nomeArquivo))) {
                    writer.write("1 - " + petAtualizado.getNomeCompleto() + "\n");
                    writer.write("2 - " + petAtualizado.getTipo() + "\n");
                    writer.write("3 - " + petAtualizado.getSexo() + "\n");
                    writer.write("4 - " + petAtualizado.getEnderecoBairro() + "\n");
                    writer.write("5 - " + petAtualizado.getIdade() + "\n");
                    writer.write("6 - " + petAtualizado.getPeso() + "\n");
                    writer.write("7 - " + petAtualizado.getRaca() + "\n");

                    System.out.println("Dados do pet atualizados e salvos com sucesso em: " + nomeArquivo);
                } catch (IOException e) {
                    System.err.println("Ocorreu um erro ao salvar o arquivo: " + e.getMessage());
                }

            } else {
                System.out.println("Escolha inválida. Por favor, digite um número da lista.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite apenas números.");
            scanner.nextLine();
        }
    }

    public void listarTodosPets() {
        System.out.println("\n--- LISTA DE TODOS OS PETS ---");

        File pastaPets = new File("petsCadastrados");

        if (!pastaPets.exists() || !pastaPets.isDirectory()) {
            System.out.println("Nenhum pet cadastrado encontrado.");
            return;
        }

        File[] arquivosPets = pastaPets.listFiles();

        if (arquivosPets == null || arquivosPets.length == 0) {
            System.out.println("Nenhum pet cadastrado encontrado.");
            return;
        }

        System.out.println("Pets encontrados:");

        int totalPets = 0;
        for (File arquivo : arquivosPets) {
            Pet pet = lerPetDoArquivo(arquivo);
            if (pet != null) {
                exibirDetalhesDoPet(pet); // Reutiliza o método de exibição
                totalPets++;
            }
        }

        if (totalPets == 0) {
            System.out.println("Nenhum pet encontrado.");
        }
    }

    public void deletarPet() {
        // Reutiliza o método de busca
        List<File> resultados = encontrarPetsPorCriterio();

        if (resultados.isEmpty()) {
            System.out.println("Nenhum pet encontrado para exclusão.");
            return;
        }

        System.out.println("\nPets encontrados para exclusão:");
        for (int i = 0; i < resultados.size(); i++) {
            File arquivo = resultados.get(i);
            Pet pet = lerPetDoArquivo(arquivo);
            System.out.println((i + 1) + ". " + pet.getNomeCompleto() + " - " + pet.getTipo());
        }

        System.out.print("\nDigite o número do pet que deseja excluir: ");
        try {
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha > 0 && escolha <= resultados.size()) {
                File arquivoParaDeletar = resultados.get(escolha - 1);
                Pet petParaDeletar = lerPetDoArquivo(arquivoParaDeletar);

                System.out.print("Tem certeza que deseja excluir " + petParaDeletar.getNomeCompleto() + "? (S/N): ");
                String confirmacao = scanner.nextLine();

                if (confirmacao.equalsIgnoreCase("s")) {
                    if (arquivoParaDeletar.delete()) {
                        System.out.println("Pet excluído com sucesso!");
                    } else {
                        System.out.println("Erro ao excluir o pet.");
                    }
                } else {
                    System.out.println("Exclusão cancelada.");
                }
            } else {
                System.out.println("Escolha inválida. Por favor, digite um número da lista.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite apenas números.");
            scanner.nextLine();
        }
    }

    private void exibirMenuFormulario() {
        int opcao;
        do {
            System.out.println("==== MENU DO FORMULÁRIO ====");
            System.out.println("1 - Criar nova pergunta");
            System.out.println("2 - Alterar pergunta existente");
            System.out.println("3 - Excluir pergunta existente");
            System.out.println("4 - Voltar ao menu principal");

            try {
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        criarNovaPergunta();
                        break;
                    case 2:
                        alterarPergunta();
                        break;
                    case 3:
                        excluirPergunta();
                        break;
                    case 4:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
                scanner.nextLine();
                opcao = 0;
            }
        } while (opcao != 4);
    }

    private void criarNovaPergunta() {
        System.out.println("\n--- Criar Nova Pergunta ---");
        List<String> perguntas = lerPerguntasDoArquivo();

        System.out.print("Digite a nova pergunta: ");
        String novaPergunta = scanner.nextLine();

        int novoNumero = perguntas.size() + 1;
        perguntas.add(novoNumero + " - " + novaPergunta);

        reescreverArquivoFormulario(perguntas);
        System.out.println("Nova pergunta adicionada com sucesso!");
    }

    private void alterarPergunta() {
        System.out.println("\n--- Alterar Pergunta ---");
        List<String> perguntas = lerPerguntasDoArquivo();

        for (String pergunta : perguntas) {
            System.out.println(pergunta);
        }

        System.out.print("\nDigite o número da pergunta que deseja alterar (8 ou superior): ");
        try {
            int numeroPergunta = scanner.nextInt();
            scanner.nextLine();

            if (numeroPergunta < 8 || numeroPergunta > perguntas.size()) {
                System.out.println("Pergunta inválida. Somente perguntas adicionadas podem ser alteradas.");
                return;
            }

            System.out.print("Digite o novo texto para a pergunta: ");
            String novoTexto = scanner.nextLine();

            perguntas.set(numeroPergunta - 1, numeroPergunta + " - " + novoTexto);

            reescreverArquivoFormulario(perguntas);
            System.out.println("Pergunta alterada com sucesso!");

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Digite apenas números.");
            scanner.nextLine();
        }
    }

    private void excluirPergunta() {
        System.out.println("\n--- Excluir Pergunta ---");
        List<String> perguntas = lerPerguntasDoArquivo();

        for (String pergunta : perguntas) {
            System.out.println(pergunta);
        }

        System.out.print("\nDigite o número da pergunta que deseja excluir (8 ou superior): ");
        try {
            int numeroPergunta = scanner.nextInt();
            scanner.nextLine();

            if (numeroPergunta < 8 || numeroPergunta > perguntas.size()) {
                System.out.println("Pergunta inválida. Somente perguntas adicionadas podem ser excluídas.");
                return;
            }

            System.out.print("Tem certeza que deseja excluir esta pergunta? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                perguntas.remove(numeroPergunta - 1);

                // Renumera as perguntas restantes
                for (int i = numeroPergunta - 1; i < perguntas.size(); i++) {
                    String perguntaAtual = perguntas.get(i);
                    // Pega o texto da pergunta e remove o número antigo
                    String textoSemNumero = perguntaAtual.substring(perguntaAtual.indexOf("-") + 1).trim();
                    perguntas.set(i, (i + 1) + " - " + textoSemNumero);
                }

                reescreverArquivoFormulario(perguntas);
                System.out.println("Pergunta excluída com sucesso!");
            } else {
                System.out.println("Exclusão cancelada.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Digite apenas números.");
            scanner.nextLine();
        }
    }

    // Metodo auxiliar para ler todas as perguntas do arquivo
    private List<String> lerPerguntasDoArquivo() {
        List<String> perguntas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/formulario.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                perguntas.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de formulário: " + e.getMessage());
        }
        return perguntas;
    }

    // Metodo auxiliar para reescrever o arquivo com a lista atualizada
    private void reescreverArquivoFormulario(List<String> perguntas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/formulario.txt"))) {
            for (String pergunta : perguntas) {
                writer.write(pergunta + "\n");
            }
        } catch (IOException e) {
            System.err.println("Erro ao reescrever o arquivo de formulário: " + e.getMessage());
        }
    }
}