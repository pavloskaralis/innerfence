require 'json'
package = JSON.parse(File.read('../package.json'))

Pod::Spec.new do |s|
  s.name         = "RNInnerfence"
  s.version      =  package["version"]
  s.summary      = package["description"]
  s.description  = <<-DESC
                  RNInnerfence
                   DESC
  s.homepage     = "n/a"
  s.license      = package['license']
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "bhavik" => "bhavik@tryhungry.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/BhavikBhatt/innerfence-ios.git", :tag => "master" }
  s.source_files  = "RNInnerfence/**/*.{h,m}"
  s.requires_arc = true



  s.dependency "React"
  #s.dependency "others"

end

  