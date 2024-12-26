import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Receituario {
    private int id;
    private LocalDate dataEmissao;
    private int idMedico;
    private String status;
    private String observacoes;
    private List<ItemReceituario> itens;

    public Receituario(int id, LocalDate dataEmissao, int idMedico) {
        this.id = id;
        setDataEmissao(dataEmissao);
        this.idMedico = idMedico;
        this.status = "Emitido"; // Status padrão ao criar
        this.itens = new ArrayList<>();
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        if (dataEmissao.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de emissão não pode ser futura.");
        }
        this.dataEmissao = dataEmissao;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (!status.equals("Emitido") && !status.equals("Cancelado")) {
            throw new IllegalArgumentException("Status inválido. Deve ser 'Emitido' ou 'Cancelado'.");
        }
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public List<ItemReceituario> getItens() {
        return Collections.unmodifiableList(itens); // Lista imutável para evitar manipulação externa
    }

    public void adicionarItem(ItemReceituario item) {
        if (item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade de itens deve ser positiva.");
        }
        itens.add(item);
    }

    public double calcularValorTotal() {
        return itens.stream()
                .mapToDouble(item -> item.getQuantidade() * item.getMedicamento().getPreco())
                .sum();
    }

    public void imprimir() {
        System.out.println("ID: " + id);
        System.out.println("Data de Emissão: " + dataEmissao);
        System.out.println("ID do Médico: " + idMedico);
        System.out.println("Status: " + status);
        System.out.println("Observações: " + (observacoes != null ? observacoes : "Nenhuma"));
        System.out.println("Itens do Receituário:");
        if (itens.isEmpty()) {
            System.out.println("Nenhum item registrado.");
        } else {
            for (ItemReceituario item : itens) {
                System.out.println(item);
            }
        }
        System.out.printf("Valor Total: R$ %.2f%n", calcularValorTotal());
    }

    public boolean isAtivo() {
        return "Emitido".equals(status);
    }

    public void cancelar() {
        setStatus("Cancelado");
    }

    public ItemReceituario getItemByMedicamento(String nomeMedicamento) {
        return itens.stream()
                .filter(item -> item.getMedicamento().getNome().equalsIgnoreCase(nomeMedicamento))
                .findFirst()
                .orElse(null);
    }

    // Método para filtrar receituários por data de emissão
    public static boolean filtrarPorData(Receituario receituario, LocalDate dataInicio, LocalDate dataFim) {
        return !receituario.getDataEmissao().isBefore(dataInicio) && !receituario.getDataEmissao().isAfter(dataFim);
    }

    // Método para emitir um receituário e adicionar itens a ele
    public static Receituario emitirReceituario(int id, int idMedico, List<Medicamento> medicamentos, Scanner scanner) {
        Receituario receituario = new Receituario(id, LocalDate.now(), idMedico);

        while (true) {
            System.out.print("Digite o nome do medicamento (ou 'sair' para finalizar): ");
            String nomeMedicamento = scanner.nextLine();

            if (nomeMedicamento.equalsIgnoreCase("sair")) {
                break;
            }

            Medicamento medicamento = medicamentos.stream()
                    .filter(m -> m.getNome().equalsIgnoreCase(nomeMedicamento))
                    .findFirst()
                    .orElse(null);

            if (medicamento != null) {
                System.out.print("Digite a quantidade: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                if (quantidade > 0) {
                    ItemReceituario itemReceituario = new ItemReceituario(medicamento, quantidade);
                    receituario.adicionarItem(itemReceituario);
                    System.out.println("Item adicionado ao receituário.");
                } else {
                    System.out.println("Quantidade inválida. Tente novamente.");
                }
            } else {
                System.out.println("Medicamento não encontrado.");
            }
        }

        System.out.println("Receituário emitido com sucesso!");
        receituario.imprimir();
        return receituario;
    }
}
