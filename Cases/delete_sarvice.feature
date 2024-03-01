Feature: Delete sarvice
  Scenario: delete Successfully
    Given the sarvice list have sarvice
    When the admin or sarvice provider want to delete sarvice with id 5
    Then delete done

  Scenario: sarvice not found
    Given that the admin or sarvice provider want to delete sarvice with not valid id -1
    When the admin or sarvice provider want to delete it
    Then message appear to tell the them that is no sarvice with this id