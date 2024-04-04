package jjad.springframework.api.v1.mapper;

import jjad.springframework.api.v1.model.CategoryDTO;
import jjad.springframework.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    public static final String NAME = "Jose";
    public static final Long ID = 1L;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() {
        //given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }

    @Test
    void categoryDTOToCategory() {
        //given
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(ID);
        categoryDTO.setName(NAME);

        //when
        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);

        //then
        assertEquals(ID, category.getId());
        assertEquals(NAME, category.getName());
    }
}