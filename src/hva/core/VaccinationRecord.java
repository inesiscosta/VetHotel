package hva.core;

public class VaccinationRecord {
    private HealthStatus _damage;
    private Vaccine _administeredVaccine;
    private Veterinarian _administeringVet;
    private Animal _vaccinatedAnimal;

    public VaccinationRecord(Vaccine vaccine, Veterinarian vet, Animal animal) {
        _administeredVaccine = vaccine;
        _administeringVet = vet;
        _vaccinatedAnimal = animal;
        _damage = vaccine.calculateVacineDamage(animal);
    }

    @Override
    public String toString() {
        return "REGISTO-VACINA|" + _administeredVaccine.id() + "|" + _administeringVet.id() + "|" + _vaccinatedAnimal.getSpecies().id();
    }

    protected String getDamage() {
        return _damage.toString();
    }

    protected Veterinarian getVet() {
        return _administeringVet;
    }

    protected Animal getAnimal() {
        return _vaccinatedAnimal;
    }
}