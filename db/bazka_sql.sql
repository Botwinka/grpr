-- MySQL Script generated by MySQL Workbench
-- 01/03/17 21:21:46
-- Model: New Model    Version: 1.0
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`ad_organizacje`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ad_organizacje` ;

CREATE TABLE IF NOT EXISTS `mydb`.`ad_organizacje` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nazwa_organizacji` VARCHAR(45) NOT NULL,
  `created_by` VARCHAR(45) NULL,
  `created_date` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  `updated_date` DATETIME NULL,
  `logo` BLOB NULL,
  `nazwa_pliku_loga` VARCHAR(45) NULL,
  `data_od` DATETIME NOT NULL,
  `data_do` DATETIME NULL,
  `aktywny` TINYINT(1) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ad_uzytkownicy`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ad_uzytkownicy` ;

CREATE TABLE IF NOT EXISTS `mydb`.`ad_uzytkownicy` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `haslo` VARCHAR(225) NOT NULL,
  `email` VARCHAR(45) NULL,
  `imie` VARCHAR(45) NOT NULL,
  `nazwisko` VARCHAR(45) NOT NULL,
  `id_organizacji` BIGINT NULL,
  `created_by` VARCHAR(45) NULL,
  `created_date` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  `updated_date` DATETIME NULL,
  `aktywny` TINYINT(1) NULL,
  `data_od` DATETIME NULL,
  `data_do` DATETIME NULL,
  `locale` VARCHAR(45) NULL,
  `ilosc_prob_logowania` INT NULL,
  `data_zmiany_hasla` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fkOrganizacje_idx` (`id_organizacji` ASC),
  CONSTRAINT `fk_organizacje`
    FOREIGN KEY (`id_organizacji`)
    REFERENCES `mydb`.`ad_organizacje` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ad_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ad_role` ;

CREATE TABLE IF NOT EXISTS `mydb`.`ad_role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nazwa_roli` VARCHAR(45) NOT NULL,
  `created_by` VARCHAR(45) NULL,
  `created_date` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  `updated_date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ad_roleUzytkownicy`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ad_roleUzytkownicy` ;

CREATE TABLE IF NOT EXISTS `mydb`.`ad_roleUzytkownicy` (
  `id_roli` BIGINT NOT NULL,
  `id_uzytkownika` BIGINT NOT NULL,
  INDEX `fkUzytkownicy_idx` (`id_uzytkownika` ASC),
  INDEX `fkRole_idx` (`id_roli` ASC),
  CONSTRAINT `fk_uzytkownicy`
    FOREIGN KEY (`id_uzytkownika`)
    REFERENCES `mydb`.`ad_uzytkownicy` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role`
    FOREIGN KEY (`id_roli`)
    REFERENCES `mydb`.`ad_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ad_uprawnienia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ad_uprawnienia` ;

CREATE TABLE IF NOT EXISTS `mydb`.`ad_uprawnienia` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nazwa_uprawnienia` VARCHAR(45) NULL,
  `created_by` VARCHAR(45) NULL,
  `created_date` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  `updated_date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ad_roleUprawnienia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ad_roleUprawnienia` ;

CREATE TABLE IF NOT EXISTS `mydb`.`ad_roleUprawnienia` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_roli` BIGINT NOT NULL,
  `id_uprawnienia` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_role_idx` (`id_roli` ASC),
  INDEX `fk_uprawnienia_idx` (`id_uprawnienia` ASC),
  CONSTRAINT `fk_role_uprawnienia`
    FOREIGN KEY (`id_roli`)
    REFERENCES `mydb`.`ad_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_uprawnienia_role`
    FOREIGN KEY (`id_uprawnienia`)
    REFERENCES `mydb`.`ad_uprawnienia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`parametry_systemowe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`parametry_systemowe` ;

CREATE TABLE IF NOT EXISTS `mydb`.`parametry_systemowe` (
  `id` BIGINT NOT NULL,
  `kod_parametru` VARCHAR(45) NULL,
  `nazwa_parametru` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
