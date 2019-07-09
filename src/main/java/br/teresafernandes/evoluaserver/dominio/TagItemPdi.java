/**
 * Data: 26 de jun de 2019
 */
package br.teresafernandes.evoluaserver.dominio;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Teresa Fernandes
 *
 */
@Entity(name="tag_item_pdi")
public class TagItemPdi implements EntidadePersistente{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_item_pdi", nullable=false)
	private ItemPdi itemPdi;
	
	@ManyToOne
	@JoinColumn(name="id_tag", nullable=false)
	private Tag tag;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public ItemPdi getItemPdi() {
		return itemPdi;
	}

	public void setItemPdi(ItemPdi itemPdi) {
		this.itemPdi = itemPdi;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	@JsonProperty("itemPdi")
	public void getItemPdi(Map<String,Object> mapItemPdi) {
		this.itemPdi = null;
		if(mapItemPdi.containsKey("id")) {
			this.itemPdi = new ItemPdi();
	        this.itemPdi.setId(((Integer)mapItemPdi.get("id")).longValue());
		}
    }
	
	@JsonProperty("tag")
	public void getItemTag(Map<String,Object> mapTag) {
		this.tag = null;
		if(mapTag.containsKey("id")) {
			this.tag = new Tag();
	        this.tag.setId(((Integer)mapTag.get("id")).longValue());
		}
    }

}