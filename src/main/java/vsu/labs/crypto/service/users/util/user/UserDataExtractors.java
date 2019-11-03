package vsu.labs.crypto.service.users.util.user;

import vsu.labs.crypto.entity.security.UserEntity;
import vsu.labs.crypto.enums.RoleType;
import vsu.labs.crypto.service.users.util.ObjectDataExtractor;

import java.util.Arrays;
import java.util.List;

public interface UserDataExtractors {
    ObjectDataExtractor<UserEntity> ID_EXTRACTOR =
            new ObjectDataExtractor<>("id", UserEntity::getId);
    ObjectDataExtractor<UserEntity> NAME_EXTRACTOR =
            new ObjectDataExtractor<>("name", UserEntity::getName);
    ObjectDataExtractor<UserEntity> SURNAME_EXTRACTOR =
            new ObjectDataExtractor<>("surname", UserEntity::getSurname);
    ObjectDataExtractor<UserEntity> LOGIN_EXTRACTOR =
            new ObjectDataExtractor<>("login", UserEntity::getLogin);
    ObjectDataExtractor<UserEntity> ROLE_EXTRACTOR =
            new ObjectDataExtractor<>("role", user -> RoleType.byId(user.getRoleId()).getName());

    List<ObjectDataExtractor<UserEntity>> ALL_EXTRACTORS = Arrays.asList(
            ID_EXTRACTOR, NAME_EXTRACTOR, SURNAME_EXTRACTOR, LOGIN_EXTRACTOR, ROLE_EXTRACTOR
    );

    static String getColumnName(String key) {
        switch (key) {
            case "id": return "ID";
            case "name": return "Имя";
            case "surname": return "Фамилия";
            case "login": return "Логин";
            case "role": return "Роль";
            default: throw new IllegalStateException();
        }
    }
}
