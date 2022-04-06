package ru.geekbrains.service;

import ru.geekbrains.controller.PictureDto;

import java.util.Optional;

public interface PictureService {

    Optional<PictureDto> getPictureDataById(long id);

    String createPicture(byte[] pictureData);

    void deleteById(Long id);
}
