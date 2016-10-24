'use strict';

// Require packages
var gulp       = require('gulp');
var concat     = require('gulp-concat');
var gutil      = require('gulp-util');
var uglify     = require('gulp-uglify');
var babel      = require('gulp-babel');
var sourcemaps = require('gulp-sourcemaps');
var babelify   = require('babelify');
var browserify = require('browserify');


// Define the default task that's run on `gulp`
gulp.task('default', ['build-js', 'build-lib-js', 'build-html', 'build-html-templates', 'build-images']);

// Configure which files to watch and what tasks to use on file changes
gulp.task('watch', function() {
    gulp.watch('src/js/**/*.js',['build-js']);
    gulp.watch('src/**/*.html', ['build-html', 'build-html-templates']);
    gulp.watch('src/images/**/*.*', ['build-images']);
});

gulp.task('build-js', function() {
    return gulp.src(['src/js/**/*.js', '!src/js/lib/**/*.js'])
        .pipe(sourcemaps.init())
        .pipe(concat('workingmusician.min.js'))
        //.pipe(uglify())
        //.pipe(sourcemaps.write())
        .pipe(gulp.dest('build/js'));
});

gulp.task('build-lib-js', function() {
    return gulp.src(['!src/js/ng/**/*.js', 'src/js/lib/**/*.js'])
        //.pipe(sourcemaps.init())
        .pipe(concat('js.dependencies.min.js'))
        //.pipe(uglify())
        .pipe(sourcemaps.write())
        .pipe(gulp.dest('build/js'));
});

gulp.task('build-html', function() {
    return gulp.src(['src/*.html', 'src/user/**/*.html'])
        .pipe(gulp.dest('build/'));
});

gulp.task('build-html-templates', function() {
    return gulp.src('src/js/ng/directive/templates/**/*.html')
        .pipe(gulp.dest('build/templates'));
});

gulp.task('build-images', function() {
    return gulp.src('src/images/**/*.*')
        .pipe(gulp.dest('build/images'));
});