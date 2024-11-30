import java.time.LocalDate;
import java.util.Scanner;

public class Medicamento {
    private String id;
    private String nome;
    private String formaFarmaceutica;
    private String dosagem;
    private int quantidadeEmEstoque;
    private LocalDate dataValidade;

    public Medicamento(String id, String nome, String formaFarmaceutica, String dosagem, int quantidadeEmEstoque, LocalDate dataValidade) {
        this.id = id;
        this.nome = nome;
        this.formaFarmaceutica = formaFarmaceutica;
        this.dosagem = dosagem;
        setQuantidadeEmEstoque(quantidadeEmEstoque);
        this.dataValidade = dataValidade;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        if (quantidadeEmEstoque < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public boolean estaEmEstoque() {
        return quantidadeEmEstoque > 0;
    }

    public void adicionarEstoque(int quantidade) {
        if (quantidade > 0) {
            this.quantidadeEmEstoque += quantidade;
        } else {
            throw new IllegalArgumentException("A quantidade para adicionar deve ser positiva.");
        }
    }

    public void removerEstoque(int quantidade) {
        if (quantidade > 0 && quantidade <= this.quantidadeEmEstoque) {
            this.quantidadeEmEstoque -= quantidade;
        } else {
            throw new IllegalArgumentException("A quantidade para remover é inválida ou excede o estoque.");
        }
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public static boolean filtrarPorData(Medicamento medicamento, LocalDate dataInicio, LocalDate dataFim) {
        return !medicamento.getDataValidade().isBefore(dataInicio) && !medicamento.getDataValidade().isAfter(dataFim);
    }

    // Método para cadastrar medicamento
    public static Medicamento cadastrarMedicamento(Scanner scanner) {
        System.out.print("Digite o nome do medicamento: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a forma farmacêutica: ");
        String formaFarmaceutica = scanner.nextLine();

        System.out.print("Digite a dosagem: ");
        String dosagem = scanner.nextLine();

        System.out.print("Digite a quantidade em estoque: ");
        int quantidadeEmEstoque = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Digite a data de validade (AAAA-MM-DD): ");
        String dataValidadeStr = scanner.nextLine();
        LocalDate dataValidade = LocalDate.parse(dataValidadeStr);

        String id = "ID-" + nome.toUpperCase().substring(0, 3) + "-" + System.currentTimeMillis(); // Criando um ID único simples

        return new Medicamento(id, nome, formaFarmaceutica, dosagem, quantidadeEmEstoque, dataValidade);
    }
}