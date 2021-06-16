package udodog.goGetterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udodog.goGetterServer.model.entity.LibraryLocation;

public interface LibraryLocationRepository extends JpaRepository <LibraryLocation, Long> {
} // Interface End
