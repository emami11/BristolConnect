# BristolConnect

## Project Overview

BristolConnect is a collaborative Android application designed to make Bristol's local services more accessible to residents, students and visitors.

The application aims to provide a single platform where users can discover local facilities, report community issues and locate waste disposal services.

Rather than requiring users to navigate several separate platforms, BristolConnect brings these features together in one mobile application.

**Project Type:** University Group Coursework
**Platform:** Android
**Programming Language:** Kotlin
**UI Framework:** Jetpack Compose
**Development Environment:** Android Studio
**Version Control:** Git and GitHub

## Project Objectives

- Develop an accessible, user-friendly Android application.
- Provide information about local services and facilities in Bristol.
- Help users locate relevant facilities and obtain directions.
- Support community issue reporting.
- Help residents find appropriate waste disposal locations.
- Integrate independently developed features into one application.
- Apply collaborative development, version control and software testing practices.

## Application Modules

### 1. Local Services Finder

**Developer:** Local Services Finder team member
**Status:** Core functionality implemented and unit-tested

This module allows users to search for and explore local facilities in Bristol.

**Implemented features:**

- Facility listings for libraries and recycling centres.
- Search by facility name, category and address.
- Category-based filtering.
- Facility details, including addresses, opening hours and descriptions.
- Directions through the external Google Maps application.
- Empty search results handling.
- Dark-themed user interface.

Facility information is currently stored in a local Kotlin repository.

### 2. Report an Issue

**Developer:** Tomisin
**Status:** Awaiting integration

This module is intended to provide functionality for reporting issues within the local community.

Its implementation details and completed features will be documented after the team reviews the module's source code.

### 3. Waste Disposal Finder

**Developer:** Moses
**Status:** Awaiting integration

This module is intended to help users locate waste disposal services in Bristol.

Its implemented features will be documented once the module is available in the shared repository.

## Technology Stack

| Technology        | Purpose                                  |
| ----------------- | ---------------------------------------- |
| Kotlin            | Android application development          |
| Jetpack Compose   | Declarative user interface development   |
| Android Studio    | Development, debugging and testing       |
| Material Design 3 | Android interface components             |
| Google Maps       | External directions and navigation       |
| JUnit             | Automated unit testing                   |
| Git               | Version control                          |
| GitHub            | Collaboration and source code management |

## Current Project Structure

The Local Services Finder currently follows this structure:

```text
app/
  src/
    main/
      java/
        com/example/bristolconnect/
          MainActivity.kt
          localservices/
            Facility.kt
            FacilityRepository.kt
            FacilityFilter.kt
            FacilityListScreen.kt
            FacilityDetailScreen.kt
          ui/theme/
    test/
      java/
        com/example/bristolconnect/
          FacilityRepositoryTest.kt
          FacilityFilterTest.kt
```

Additional module packages will be organised during group integration.

## Testing

Automated unit testing has been implemented for the Local Services Finder.

| Test Suite             | Tests Passed |
| ---------------------- | ------------ |
| FacilityRepositoryTest | 6            |
| FacilityFilterTest     | 9            |
| Total                  | 15           |

These tests cover facility information and repository consistency, search behaviour, category filtering, case-insensitive matching, combined filters and empty search results.

The tests previously passed in Android Studio. Further integration and user-interface testing is planned.

## Running the Application

1. Clone the BristolConnect GitHub repository.
2. Open the project in Android Studio.
3. Allow Gradle to sync and download the required dependencies.
4. Select a compatible Android emulator or connected Android device.
5. Build and run the `app` configuration.

During development, the application currently launches the Local Services Finder. A shared BristolConnect home screen will be introduced during integration.

## GitHub Collaboration

The team uses feature branches to develop separate parts of the application.

| Branch                          | Purpose                           |
| ------------------------------- | --------------------------------- |
| `main`                          | Shared application baseline       |
| `feature/local-services-finder` | Local Services Finder development |
| `feature/report-issues`         | Proposed issue reporting branch   |
| `feature/waste-disposal-finder` | Proposed waste disposal branch    |

Team members should develop and test changes on their own branches before requesting integration.

Pull requests will be used to review and combine completed work.

## Development Progress

- [x] Initial Android project created
- [x] Local Services Finder interface developed
- [x] Facility search and category filtering implemented
- [x] Facility details and external directions implemented
- [x] Local Services Finder unit tests added
- [x] Feature branch uploaded to GitHub
- [ ] Issue reporting module integrated
- [ ] Waste disposal module integrated
- [ ] Shared application navigation implemented
- [ ] Combined application integration tested
- [ ] Final application documentation completed

## Future Improvements

Potential enhancements include an embedded interactive Google Map, additional local service categories, a custom application icon and improvements informed by testing and user feedback.

These features are not yet part of the completed implementation.

## Project Status

**Active development — group integration pending.**

The Local Services Finder has implemented core functionality. The remaining modules will be reviewed and integrated into the shared BristolConnect application as development progresses.
