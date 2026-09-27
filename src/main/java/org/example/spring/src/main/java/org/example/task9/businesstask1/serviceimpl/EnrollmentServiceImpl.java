package org.example.task9.businesstask1.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.example.task9.businesstask1.enrollment.EnrollmentStatus;
import org.example.task9.businesstask1.exeption.ResourceNotFoundException;
import org.example.task9.businesstask1.model.Course;
import org.example.task9.businesstask1.model.Enrollment;
import org.example.task9.businesstask1.model.Student;
import org.example.task9.businesstask1.service.EnrollmentService;
import org.example.task9.businesstask1.repository.CourseRepository;
import org.example.task9.businesstask1.repository.EnrollmentRepository;
import org.example.task9.businesstask1.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public Enrollment enrollStudent(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id: " + courseId));

        // القاعدة 1: الطالب مينفعش يتسجل مرتين في نفس الكورس وهو لسه ENROLLED
        List<Enrollment> existing =
                enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);

        boolean alreadyEnrolled = existing.stream()
                .anyMatch(e -> e.getStatus() == EnrollmentStatus.ENROLLED);

        if (alreadyEnrolled) {
            throw new IllegalStateException(
                    "Student is already enrolled in this course");
        }

        // القاعدة 2: الكورس له سعة
        long activeCount =
                enrollmentRepository.countByCourseIdAndStatus(courseId, EnrollmentStatus.ENROLLED);

        if (activeCount >= course.getMaxStudents()) {
            throw new IllegalStateException("Course is full");
        }

        // القاعدة 3: الكورس متاح لمستوى معين بس
        if (course.getRequiredLevel() != null &&
                !course.getRequiredLevel().equals(student.getLevel())) {

            throw new IllegalStateException(
                    "This course is not available for student's level");
        }

        // القاعدة 4: لازم يكون خلص ونجح في كل الـ prerequisites
        if (course.getPrerequisites() != null && !course.getPrerequisites().isEmpty()) {

            for (Course prerequisite : course.getPrerequisites()) {

                List<Enrollment> prereqEnrollments =
                        enrollmentRepository.findByStudentIdAndCourseId(studentId, prerequisite.getId());

                boolean passedPrerequisite = prereqEnrollments.stream()
                        .anyMatch(e -> e.getStatus() == EnrollmentStatus.COMPLETED);

                if (!passedPrerequisite) {
                    throw new IllegalStateException(
                            "Student must complete prerequisite course: " + prerequisite.getTitle());
                }
            }
        }

        // كل حاجة تمام، سجّلي الطالب
        Enrollment enrollment = new Enrollment(
                student, course, EnrollmentStatus.ENROLLED, LocalDate.now());

        return enrollmentRepository.save(enrollment);
    }

    @Override
    @Transactional
    public Enrollment dropCourse(Long enrollmentId) {

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Enrollment not found with id: " + enrollmentId));

        if (enrollment.getStatus() != EnrollmentStatus.ENROLLED) {
            throw new IllegalStateException("Only active enrollments can be dropped");
        }

        // القاعدة: drop مسموح بس قبل الـ deadline بتاع الكورس
        LocalDate deadline = enrollment.getCourse().getDropDeadline();

        if (deadline != null && LocalDate.now().isAfter(deadline)) {
            throw new IllegalStateException("Drop deadline has passed for this course");
        }

        enrollment.setStatus(EnrollmentStatus.DROPPED);
        enrollment.setDropDate(LocalDate.now());

        return enrollmentRepository.save(enrollment);

        // ملحوظة: المكان (seat) بيرجع متاح تلقائيًا،
        // لأننا بنعد بس الـ ENROLLED وقت ما نحسب الـ activeCount في enrollStudent
    }

    @Override
    @Transactional
    public Enrollment markAsCompleted(Long enrollmentId) {

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Enrollment not found with id: " + enrollmentId));

        if (enrollment.getStatus() != EnrollmentStatus.ENROLLED) {
            throw new IllegalStateException("Only active enrollments can be completed");
        }

        enrollment.setStatus(EnrollmentStatus.COMPLETED);

        return enrollmentRepository.save(enrollment);
    }

    @Override
    @Transactional
    public Enrollment markAsFailed(Long enrollmentId) {

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Enrollment not found with id: " + enrollmentId));

        if (enrollment.getStatus() != EnrollmentStatus.ENROLLED) {
            throw new IllegalStateException("Only active enrollments can be marked as failed");
        }

        enrollment.setStatus(EnrollmentStatus.FAILED);

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getStudentEnrollments(Long studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }

        return enrollmentRepository.findByStudentId(studentId);
    }
}
