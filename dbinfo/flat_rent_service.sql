-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema flat_rent_service
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema flat_rent_service
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `flat_rent_service` DEFAULT CHARACTER SET utf8 ;
USE `flat_rent_service` ;

-- -----------------------------------------------------
-- Table `flat_rent_service`.`users_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flat_rent_service`.`users_role` (
  `users_role_id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`users_role_id`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC) VISIBLE,
  UNIQUE INDEX `users_role_id_UNIQUE` (`users_role_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flat_rent_service`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flat_rent_service`.`users` (
  `users_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `phone` VARCHAR(17) NOT NULL,
  `users_role_id` INT NOT NULL DEFAULT 2,
  `is_activated` TINYINT NOT NULL DEFAULT 0,
  `is_banned` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`users_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE,
  INDEX `fk_users_users_role_idx` (`users_role_id` ASC) VISIBLE,
  UNIQUE INDEX `users_id_UNIQUE` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_users_role`
    FOREIGN KEY (`users_role_id`)
    REFERENCES `flat_rent_service`.`users_role` (`users_role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flat_rent_service`.`flats_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flat_rent_service`.`flats_address` (
  `flats_address_id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `district` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `house` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`flats_address_id`),
  UNIQUE INDEX `flats_address_id_UNIQUE` (`flats_address_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flat_rent_service`.`flats_description`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flat_rent_service`.`flats_description` (
  `flats_description_id` INT NOT NULL AUTO_INCREMENT,
  `rooms` INT NOT NULL,
  `living_area` FLOAT NOT NULL,
  `has_furniture` TINYINT NOT NULL,
  `has_home_appliciances` TINYINT NOT NULL,
  `has_the_internet` TINYINT NOT NULL,
  `possible_with_childs` TINYINT NOT NULL,
  `possible_with_pets` TINYINT NOT NULL,
  `users_description` TEXT NOT NULL,
  PRIMARY KEY (`flats_description_id`),
  UNIQUE INDEX `flats_description_id_UNIQUE` (`flats_description_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flat_rent_service`.`flats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flat_rent_service`.`flats` (
  `flats_id` INT NOT NULL AUTO_INCREMENT,
  `is_free` TINYINT NOT NULL DEFAULT 1,
  `flats_description_id` INT NOT NULL,
  `flats_address_id` INT NOT NULL,
  PRIMARY KEY (`flats_id`),
  UNIQUE INDEX `flats_id_UNIQUE` (`flats_id` ASC) VISIBLE,
  INDEX `fk_flats_flats_address1_idx` (`flats_address_id` ASC) VISIBLE,
  CONSTRAINT `fk_flats_flats_address1`
    FOREIGN KEY (`flats_address_id`)
    REFERENCES `flat_rent_service`.`flats_address` (`flats_address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flats_flats_description2`
    FOREIGN KEY (`flats_description_id`)
    REFERENCES `flat_rent_service`.`flats_description` (`flats_description_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flat_rent_service`.`flats_photo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flat_rent_service`.`flats_photo` (
  `flats_photo_id` INT NOT NULL AUTO_INCREMENT,
  `flats_id` INT NOT NULL,
  `photo` LONGBLOB NOT NULL,
  PRIMARY KEY (`flats_photo_id`),
  UNIQUE INDEX `flats_photo_id_UNIQUE` (`flats_photo_id` ASC) VISIBLE,
  INDEX `fk_flats_photo_flats1_idx` (`flats_id` ASC) VISIBLE,
  CONSTRAINT `fk_flats_photo_flats1`
    FOREIGN KEY (`flats_id`)
    REFERENCES `flat_rent_service`.`flats` (`flats_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flat_rent_service`.`advertisements`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flat_rent_service`.`advertisements` (
  `advertisements_id` INT NOT NULL AUTO_INCREMENT,
  `author_id` INT NOT NULL,
  `flats_id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `price` DECIMAL NOT NULL,
  `is_visible` TINYINT NOT NULL DEFAULT 1,
  `date_of_creation` BIGINT NOT NULL,
  PRIMARY KEY (`advertisements_id`),
  UNIQUE INDEX `advertisements_id_UNIQUE` (`advertisements_id` ASC) VISIBLE,
  INDEX `fk_advertisements_flats1_idx` (`flats_id` ASC) VISIBLE,
  INDEX `fk_advertisements_users1_idx` (`author_id` ASC) VISIBLE,
  CONSTRAINT `fk_advertisements_flats1`
    FOREIGN KEY (`flats_id`)
    REFERENCES `flat_rent_service`.`flats` (`flats_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_advertisements_users1`
    FOREIGN KEY (`author_id`)
    REFERENCES `flat_rent_service`.`users` (`users_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flat_rent_service`.`requests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flat_rent_service`.`requests` (
  `requests_id` INT NOT NULL AUTO_INCREMENT,
  `users_id` INT NOT NULL,
  `start_date` BIGINT NOT NULL,
  `end_date` BIGINT NULL,
  `advertisements_id` INT NOT NULL,
  `application_date` BIGINT NOT NULL,
  `is_approved` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`requests_id`),
  UNIQUE INDEX `requests_id_UNIQUE` (`requests_id` ASC) VISIBLE,
  INDEX `fk_requests_advertisements1_idx` (`advertisements_id` ASC) VISIBLE,
  INDEX `fk_requests_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_requests_advertisements1`
    FOREIGN KEY (`advertisements_id`)
    REFERENCES `flat_rent_service`.`advertisements` (`advertisements_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_requests_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `flat_rent_service`.`users` (`users_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `users_role` VALUES (null, "ADMIN"), (null, "USER"), (null, "GUEST");
INSERT INTO  `users` VALUE (null, "ADMIN","ADMIN", "admin@gmail.com", "827ccb0eea8a706c4c34a16891f84e7b", "+375(29)111-11-11",  1, 1, 0);