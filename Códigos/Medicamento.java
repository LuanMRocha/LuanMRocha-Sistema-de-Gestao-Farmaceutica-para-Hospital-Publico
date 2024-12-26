import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Medicamento {
    private String id;
    private String nome;
    private String formaFarmaceutica;
    private String dosagem;
    private int quantidadeEmEstoque;
    private LocalDate dataValidade;

    // Construtor
    public Medicamento(String id, String nome, String formaFarmaceutica, String dosagem, int quantidadeEmEstoque, LocalDate dataValidade) {
        this.id = id;
        this.nome = nome;
        this.formaFarmaceutica = formaFarmaceutica;
        this.dosagem = dosagem;
        setQuantidadeEmEstoque(quantidadeEmEstoque);
        this.dataValidade = dataValidade;
    }

    // Getters e Setters
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

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    // Verifica se o medicamento está em estoque
    public boolean estaEmEstoque() {
        return quantidadeEmEstoque > 0;
    }

    // Adiciona ao estoque
    public void adicionarEstoque(int quantidade) {
        if (quantidade > 0) {
            this.quantidadeEmEstoque += quantidade;
        } else {
            throw new IllegalArgumentException("A quantidade para adicionar deve ser positiva.");
        }
    }

    // Remove do estoque
    public void removerEstoque(int quantidade) {
        if (quantidade > 0 && quantidade <= this.quantidadeEmEstoque) {
            this.quantidadeEmEstoque -= quantidade;
        } else {
            throw new IllegalArgumentException("A quantidade para remover é inválida ou excede o estoque.");
        }
    }

    // Método para filtrar por validade
    public boolean filtrarPorData(LocalDate dataInicio, LocalDate dataFim) {
        return !this.dataValidade.isBefore(dataInicio) && !this.dataValidade.isAfter(dataFim);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Forma Farmacêutica: " + formaFarmaceutica + ", Dosagem: " + dosagem
                + ", Quantidade em Estoque: " + quantidadeEmEstoque + ", Data de Validade: " + dataValidade;
    }
}

// Classe para gerenciar os medicamentos
class MedicamentoService {
    private List<Medicamento> medicamentos;

    // Construtor
    public MedicamentoService() {
        this.medicamentos = new ArrayList<>();
    }

    // Adicionar um medicamento
    public void adicionarMedicamento(Medicamento medicamento) {
        medicamentos.add(medicamento);
    }

    // Remover um medicamento pelo ID
    public boolean removerMedicamento(String id) {
        return medicamentos.removeIf(medicamento -> medicamento.getId().equals(id));
    }

    // Atualizar informações de um medicamento pelo ID
    public boolean atualizarMedicamento(String id, Scanner scanner) {
        for (Medicamento medicamento : medicamentos) {
            if (medicamento.getId().equals(id)) {
                System.out.print("Digite o novo nome: ");
                medicamento.setNome(scanner.nextLine());

                System.out.print("Digite a nova forma farmacêutica: ");
                medicamento.setFormaFarmaceutica(scanner.nextLine());

                System.out.print("Digite a nova dosagem: ");
                medicamento.setDosagem(scanner.nextLine());

                System.out.print("Digite a nova quantidade em estoque: ");
                int novaQuantidade = scanner.nextInt();
                medicamento.setQuantidadeEmEstoque(novaQuantidade);

                scanner.nextLine(); // Consumir nova linha
                System.out.print("Digite a nova data de validade (AAAA-MM-DD): ");
                medicamento.setDataValidade(LocalDate.parse(scanner.nextLine()));

                return true;
            }
        }
        return false; // Medicamento não encontrado
    }

    // Listar todos os medicamentos
    public void listarMedicamentos() {
        if (medicamentos.isEmpty()) {
            System.out.println("Nenhum medicamento cadastrado.");
        } else {
            for (Medicamento medicamento : medicamentos) {
                System.out.println(medicamento);
            }
        }
    }
}
