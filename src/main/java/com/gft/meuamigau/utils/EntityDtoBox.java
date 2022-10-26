package com.gft.meuamigau.utils;

import com.gft.meuamigau.enums.EntityDtoBoxType;

/**
 * This class creates the possibility of returning two types of objects,
 * an entity and a dto in the same method, it can be used to contain
 * one or both objects.
 * "E" represents the entity and "D" represents DTO
 */
public class EntityDtoBox<E,D> {
	
	private E e;
    private D d;

    public E getEntity() {
    	return e;
    } 

    public D getDto() {
    	return d;
    } 
    
    public void setEntity(E e) {
    	this.e = e; 
    }
    
    public void setDto(D d) {
    	this.d = d; 
    }
  
    public EntityDtoBox() {}
    
	@SuppressWarnings("unchecked")
	public EntityDtoBox(Object obj, EntityDtoBoxType type){
    	switch (type) {
		case ENTITY: {
			this.e = (E) obj;
			break;
		}
		case DTO: {
			this.d = (D) obj;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type.toString());
		}
    }
    
    public EntityDtoBox(E entity, D dto){
		this.e = entity;
		this.d = dto;
    }

}
