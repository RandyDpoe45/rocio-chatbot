package com.wesdom.rocio.database.repositories;

import com.wesdom.rocio.model.ImageDescriptor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ImageDescriptorRepository {

    Page<ImageDescriptor> getAll(Map<String,String> params);
    ImageDescriptor create(ImageDescriptor imageDescriptor);

}
