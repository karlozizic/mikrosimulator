package project.backend.model;


import jakarta.persistence.*;

//Za UredajZaPrikupljanjePodataka, Kameru, Primopredajnik, Klasifikator sam koristio strategiju Single Table - dakle svi Entiteti ce se nalaziti u jednoj tablici unutar baze
//vidi jos je li to dobro? - medutim strategije Table Per Class, MappedSupperClass nisam uspio implementirati
//https:www.baeldung.com/hibernate-inheritance
//https://thorben-janssen.com/complete-guide-inheritance-strategies-jpa-hibernate/

@Entity(name="uredaj")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="uredaj_type",
		discriminatorType = DiscriminatorType.INTEGER)
public class Uredaj {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(name="uredaj_type", insertable = false, updatable = false)
	protected int uredajType;

	public Uredaj(Long uredajId) {
		super();
		this.id = uredajId;
	}

	public Uredaj(){}

	public Long getUredajId() {
		return id;
	}

	public void setUredajId(Long uredajId) {
		this.id = uredajId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getUredajType() {
		return uredajType;
	}

}
