package project.backend.model;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

	private boolean kvar;

	private float razinaPouzdanosti;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn
//	@JoinColumn(name="naplatna_tocka_id")
	private NaplatnaTocka naplatnaTocka;

	@Column(name="uredaj_type", insertable = false, updatable = false)
	protected int uredajType;

	public Uredaj(Long uredajId, NaplatnaTocka naplatnaTocka, boolean kvar, float razinaPouzdanosti) {
		super();
		this.id = uredajId;
		this.naplatnaTocka = naplatnaTocka;
		this.kvar = kvar;
		this.razinaPouzdanosti = razinaPouzdanosti;
	}
	public Uredaj(NaplatnaTocka naplatnaTocka, boolean kvar, float razinaPouzdanosti) {
		super();
		this.naplatnaTocka = naplatnaTocka;
		this.kvar = kvar;
		this.razinaPouzdanosti = razinaPouzdanosti;
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

	public NaplatnaTocka getNaplatnaTocka() {
		return naplatnaTocka;
	}

	public void setNaplatnaTocka(NaplatnaTocka naplatnaTocka) {
		this.naplatnaTocka = naplatnaTocka;
	}

	public boolean isKvar() {
		return kvar;
	}

	public void setKvar(boolean kvar) {
		this.kvar = kvar;
	}

	public float getRazinaPouzdanosti() {
		return razinaPouzdanosti;
	}

	public void setRazinaPouzdanosti(float razinaPouzdanosti) {
		this.razinaPouzdanosti = razinaPouzdanosti;
	}
}
