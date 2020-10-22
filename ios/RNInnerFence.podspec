require 'json'

package = JSON.parse(File.read('../package.json'))
Pod::Spec.new do |s|
  s.name         = "RNInnerFence"
  s.version      =  package["version"]
  s.summary      = "summary"
  s.description  = <<-DESC
                  RNInnerFence
                   DESC
  s.homepage     = "n/a"
  s.license      = package['license']
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "bhavik" => "bhavik@tryhungry.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/pavloskaralis/innerfence", :tag => "main" }
  s.source_files  = "RNInnerFence/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  