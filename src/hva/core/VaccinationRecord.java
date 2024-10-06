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

    String damage() {
        return _damage.toString();
    }

    Veterinarian vet() {
        return _administeringVet;
    }

    Animal animal() {
        return _vaccinatedAnimal;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("REGISTO-VACINA|")
            .append(_administeredVaccine.id()).append("|")
            .append(_administeringVet.id()).append("|")
            .append(_vaccinatedAnimal.species().id());
        return result.toString();
    }
}