
CREATE TABLE IF NOT EXISTS `airlinedb`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
plane
CREATE TABLE IF NOT EXISTS `airlinedb`.`user` (
  `login` VARCHAR(12) NOT NULL,
  `pass` VARCHAR(12) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`login`, `role_id`),
  INDEX `fk_user_role1_idx` (`role_id` ASC),
  CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `airlinedb`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
