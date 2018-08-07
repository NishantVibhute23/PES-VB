ALTER TABLE `Teams` DROP FOREIGN KEY `Teams_fk0`;

ALTER TABLE `Players` DROP FOREIGN KEY `Players_fk0`;

ALTER TABLE `Matches` DROP FOREIGN KEY `Matches_fk0`;

ALTER TABLE `Matches` DROP FOREIGN KEY `Matches_fk1`;

ALTER TABLE `Matches` DROP FOREIGN KEY `Matches_fk2`;

ALTER TABLE `match_evaluation` DROP FOREIGN KEY `match_evaluation_fk0`;

ALTER TABLE `Rally` DROP FOREIGN KEY `Rally_fk0`;

ALTER TABLE `Rally_details` DROP FOREIGN KEY `Rally_details_fk0`;

ALTER TABLE `Rally_details` DROP FOREIGN KEY `Rally_details_fk1`;

ALTER TABLE `Rally_details` DROP FOREIGN KEY `Rally_details_fk2`;

ALTER TABLE `m_skills_desc_criteria` DROP FOREIGN KEY `m_skills_desc_criteria_fk0`;

ALTER TABLE `m_skill_details` DROP FOREIGN KEY `m_skill_details_fk0`;

ALTER TABLE `m_skill_details` DROP FOREIGN KEY `m_skill_details_fk1`;

ALTER TABLE `m_skill_desc_criteria_point` DROP FOREIGN KEY `m_skill_desc_criteria_point_fk0`;

ALTER TABLE `rally_details_criteria` DROP FOREIGN KEY `rally_details_criteria_fk0`;

ALTER TABLE `rally_details_criteria` DROP FOREIGN KEY `rally_details_criteria_fk1`;

DROP TABLE IF EXISTS `Competition`;

DROP TABLE IF EXISTS `Teams`;

DROP TABLE IF EXISTS `Players`;

DROP TABLE IF EXISTS `User`;

DROP TABLE IF EXISTS `Matches`;

DROP TABLE IF EXISTS `match_evaluation`;

DROP TABLE IF EXISTS `Rally`;

DROP TABLE IF EXISTS `Rally_details`;

DROP TABLE IF EXISTS `m_rating`;

DROP TABLE IF EXISTS `m_skills`;

DROP TABLE IF EXISTS `m_skills_desc_criteria`;

DROP TABLE IF EXISTS `m_skill_details`;

DROP TABLE IF EXISTS `m_skill_desc_criteria_point`;

DROP TABLE IF EXISTS `rally_details_criteria`;

