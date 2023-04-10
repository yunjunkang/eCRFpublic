package com.evertri.ecrf.repository;

import com.evertri.ecrf.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

    Form findByName(String name);
    List<Form> findByNameContaining(String name);
    List<Form> findByDescriptionContaining(String description);

    // Custom methods

    /**
     * Returns a List of all Forms that were created by a specific User.
     *
     * @param userId The id of the User who created the Forms.
     * @return A List of all Forms created by the specified User.
     */
    List<Form> findByUserId(Long userId);

    /**
     * Returns a List of all Forms that have a specific status.
     *
     * @param status The status of the Forms.
     * @return A List of all Forms with the specified status.
     */
    List<Form> findByStatus(String status);

    /**
     * Returns a List of all Forms that were created within a specific date range.
     *
     * @param start The start date of the date range.
     * @param end The end date of the date range.
     * @return A List of all Forms created within the specified date range.
     */
    List<Form> findByCreationDateBetween(LocalDate start, LocalDate end);

    /**
     * Returns a List of all Forms that contain a specific keyword in either their name or description.
     *
     * @param nameKeyword The keyword to search for in the name of the Forms.
     * @param descriptionKeyword The keyword to search for in the description of the Forms.
     * @return A List of all Forms that contain the specified keyword.
     */
    List<Form> findByNameContainingOrDescriptionContaining(String nameKeyword, String descriptionKeyword);
}
