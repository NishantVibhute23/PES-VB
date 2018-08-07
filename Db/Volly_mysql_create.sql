CREATE TABLE `Competition` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`venue` VARCHAR(255) NOT NULL,
	`start_date` DATE NOT NULL,
	`end_date` DATE NOT NULL,
	`phase` DATE NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Teams` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`competition_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Players` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`chest_num` INT NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`team_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `User` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`email_id` VARCHAR(255) NOT NULL,
	`key` VARCHAR(255) NOT NULL,
	`is_activated` INT NOT NULL,
	`created_on` TIMESTAMP NOT NULL DEFAULT 'current_timestamp',
	PRIMARY KEY (`id`)
);

CREATE TABLE `Matches` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`date` DATE NOT NULL,
	`time` TIME NOT NULL,
	`competation_id` INT NOT NULL,
	`team1` INT NOT NULL,
	`team2` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `match_evaluation` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`match_id` INT NOT NULL,
	`evaluation_team_id` INT NOT NULL,
	`set_no` INT NOT NULL,
	`score` INT NOT NULL,
	`won_by` INT NOT NULL,
	`evaluator` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Rally` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`number` INT NOT NULL,
	`result` INT NOT NULL,
	`evaluation_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Rally_details` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`skill` INT NOT NULL,
	`chest_no` INT NOT NULL,
	`rating` INT NOT NULL,
	`rally_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `m_rating` (
	`id` INT NOT NULL,
	`grade` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `m_skills` (
	`id` INT NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `m_skills_desc_criteria` (
	`id` INT NOT NULL,
	`type` VARCHAR(255) NOT NULL,
	`skill_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `m_skill_details` (
	`id` INT NOT NULL,
	`skill_id` INT NOT NULL,
	`rating_id` INT NOT NULL,
	`name` varchar(100) NOT NULL,
	`descrtiption` TEXT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `m_skill_desc_criteria_point` (
	`id` INT NOT NULL,
	`type` varchar(100) NOT NULL,
	`abbrevation` varchar(20) NOT NULL,
	`skill_desc_criteria_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `rally_details_criteria` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`skill_desc_criteria_id` INT NOT NULL,
	`type` varchar(20) NOT NULL,
	`rally_details_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `Teams` ADD CONSTRAINT `Teams_fk0` FOREIGN KEY (`competition_id`) REFERENCES `Competition`(`id`);

ALTER TABLE `Players` ADD CONSTRAINT `Players_fk0` FOREIGN KEY (`team_id`) REFERENCES `Teams`(`id`);

ALTER TABLE `Matches` ADD CONSTRAINT `Matches_fk0` FOREIGN KEY (`competation_id`) REFERENCES `Competition`(`id`);

ALTER TABLE `Matches` ADD CONSTRAINT `Matches_fk1` FOREIGN KEY (`team1`) REFERENCES `Teams`(`id`);

ALTER TABLE `Matches` ADD CONSTRAINT `Matches_fk2` FOREIGN KEY (`team2`) REFERENCES `Teams`(`id`);

ALTER TABLE `match_evaluation` ADD CONSTRAINT `match_evaluation_fk0` FOREIGN KEY (`match_id`) REFERENCES `Matches`(`id`);

ALTER TABLE `Rally` ADD CONSTRAINT `Rally_fk0` FOREIGN KEY (`evaluation_id`) REFERENCES `match_evaluation`(`id`);

ALTER TABLE `Rally_details` ADD CONSTRAINT `Rally_details_fk0` FOREIGN KEY (`skill`) REFERENCES `m_skills`(`id`);

ALTER TABLE `Rally_details` ADD CONSTRAINT `Rally_details_fk1` FOREIGN KEY (`rating`) REFERENCES `m_rating`(`id`);

ALTER TABLE `Rally_details` ADD CONSTRAINT `Rally_details_fk2` FOREIGN KEY (`rally_id`) REFERENCES `Rally`(`id`);

ALTER TABLE `m_skills_desc_criteria` ADD CONSTRAINT `m_skills_desc_criteria_fk0` FOREIGN KEY (`skill_id`) REFERENCES `m_skills`(`id`);

ALTER TABLE `m_skill_details` ADD CONSTRAINT `m_skill_details_fk0` FOREIGN KEY (`skill_id`) REFERENCES `m_skills`(`id`);

ALTER TABLE `m_skill_details` ADD CONSTRAINT `m_skill_details_fk1` FOREIGN KEY (`rating_id`) REFERENCES `m_rating`(`id`);

ALTER TABLE `m_skill_desc_criteria_point` ADD CONSTRAINT `m_skill_desc_criteria_point_fk0` FOREIGN KEY (`skill_desc_criteria_id`) REFERENCES `m_skills_desc_criteria`(`id`);

ALTER TABLE `rally_details_criteria` ADD CONSTRAINT `rally_details_criteria_fk0` FOREIGN KEY (`skill_desc_criteria_id`) REFERENCES `m_skills_desc_criteria`(`id`);

ALTER TABLE `rally_details_criteria` ADD CONSTRAINT `rally_details_criteria_fk1` FOREIGN KEY (`rally_details_id`) REFERENCES `Rally_details`(`id`);

