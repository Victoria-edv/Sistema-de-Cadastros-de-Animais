public class Pet {
    private String nomeCompleto;
    private Tipo tipo;
    private Sexo sexo;
    private String enderecoBairro;
    private int idade;
    private double peso;
    private String raca;

    public enum Tipo {
        GATO,
        CACHORRO
    }

    public enum Sexo {
        MACHO,
        FEMEA
    }

    public Pet(String nomeCompleto, Tipo tipo, Sexo sexo, String enderecoBairro, int idade, double peso, String raca) {
        this.nomeCompleto = nomeCompleto;
        this.tipo = tipo;
        this.sexo = sexo;
        this.enderecoBairro = enderecoBairro;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getEnderecoBairro() {
        return enderecoBairro;
    }

    public void setEnderecoBairro(String enderecoBairro) {
        this.enderecoBairro = enderecoBairro;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
}