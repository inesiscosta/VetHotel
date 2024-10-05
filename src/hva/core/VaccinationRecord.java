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
        _damage = vaccine.determineVaccineEffect(animal);
    }

    @Override
    public String toString() {
        return "REGISTO-VACINA|" + _administeredVaccine.id() + "|" + _administeringVet.id() + "|" + _vaccinatedAnimal.species().id();
    }

    protected String damage() {
        return _damage.toString();
    }

    protected Veterinarian vet() {
        return _administeringVet;
    }

    protected Animal animal() {
        return _vaccinatedAnimal;
    }
}