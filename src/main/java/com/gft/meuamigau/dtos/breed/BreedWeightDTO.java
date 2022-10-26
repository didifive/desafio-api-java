package com.gft.meuamigau.dtos.breed;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BreedWeightDTO {
	
  	private String imperial;
      
    private String metric;

	@Override
	public int hashCode() {
		return Objects.hash(imperial, metric);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BreedWeightDTO other = (BreedWeightDTO) obj;
		return Objects.equals(imperial, other.imperial) && Objects.equals(metric, other.metric);
	}
    
    

}
