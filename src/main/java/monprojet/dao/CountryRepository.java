package monprojet.dao;

import java.util.List;

import monprojet.dto.PopulationParPays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {

    /**
     * Calculer la population totale d'un pays
     * @param countryId
     * @return
     */
    @Query(value = "SELECT SUM(population)" +
            "from city " +
            "where country_id = :countryId", nativeQuery = true)
   /* @Query("SELECT SUM(c.population) FROM City c WHERE c.country.id = :countryId")*/
    public Integer populationTotale(Integer countryId);

    /**
     * Renvoi une liste contenant le nom du pays avec sa population
     * @return
     */
    @Query(value = "select Country.name as nom, SUM(population) as populationTotale " +
            "from Country inner join City on City.Country_id = Country.id " +
            "group by nom", nativeQuery = true)
    public List<PopulationParPays> populationParPays();

}
